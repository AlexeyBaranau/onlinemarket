package by.finalproject.service.impl;

import by.finalproject.domain.Basket;
import by.finalproject.domain.Customer;
import by.finalproject.domain.Order;
import by.finalproject.domain.OrderedProduct;
import by.finalproject.exception.EntityNotFoundException;
import by.finalproject.repository.OrderRepository;
import by.finalproject.service.BasketService;
import by.finalproject.service.CustomerService;
import by.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

import static by.finalproject.domain.OrderStatus.PROCESSING;
import static by.finalproject.domain.OrderStatus.findById;
import static java.lang.String.format;
import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final BasketService basketService;
  private final OrderRepository orderRepository;
  private final CustomerService customerService;

  @Override
  public Integer getAllOrdersCount() {
    Customer customer = customerService.getCustomer();
    return orderRepository.countAllByCustomer(customer).orElse(0);
  }

  @Override
  public Page<Order> findAllCustomerOrders(Integer page, Integer pageSize) {
    Customer customer = customerService.getCustomer();
    return orderRepository.findAllByCustomerOrderByDateDesc(customer, of(page, pageSize));
  }

  @Override
  public Order saveOrder() {
    Customer customer = customerService.getCustomer();
    Basket basket = customer.getBasket();

    final Order saveOrder =
        Order.builder()
            .customer(customer)
            .orderProductList(new ArrayList<>())
            .created(new Timestamp(System.currentTimeMillis()))
            .totalPrice(basket.getTotalPrice())
            .build();

    basket
        .getBasketItemList()
        .forEach(
            cartItem -> {
              OrderedProduct orderProduct =
                  OrderedProduct.builder()
                      .order(saveOrder)
                      .product(cartItem.getProduct())
                      .amount(cartItem.getAmount())
                      .build();
              saveOrder.getOrderProductList().add(orderProduct);
            });

    saveOrder.setStatus(PROCESSING);

    Order order = orderRepository.save(saveOrder);
    basketService.clearBasket();
    return order;
  }

  @Override
  public void changeOrderStatus(Long orderId, Integer status) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(
                () -> new EntityNotFoundException(format("Order with id %d not found", orderId)));
    order.setStatus(findById(status));
    orderRepository.save(order);
  }
}
