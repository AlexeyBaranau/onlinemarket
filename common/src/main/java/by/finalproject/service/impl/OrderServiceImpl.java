package by.finalproject.service.impl;

import by.finalproject.domain.Basket;
import by.finalproject.domain.Customer;
import by.finalproject.domain.Order;
import by.finalproject.repository.OrderRepository;
import by.finalproject.service.BasketService;
import by.finalproject.service.CustomerService;
import by.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Calendar.getInstance;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final BasketService BasketService;
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
                    .created(Timestamp.from(getInstance().getTime()))
                    .totalPrice(cart.getTotalPrice())
                    .build();

    cart.getCartItemList()
            .forEach(
                    cartItem -> {
                      final OrderProduct orderProduct =
                              OrderProduct.builder()
                                      .order(saveOrder)
                                      .product(cartItem.getProduct())
                                      .amount(cartItem.getAmount())
                                      .build();
                      saveOrder.getOrderProductList().add(orderProduct);
                    });

    saveOrder.setStatus(PROCESSING);

    final Order order = orderRepository.save(saveOrder);
    cartService.clearCart();
    return order;
  }

  @Override
  public void changeOrderStatus(String code, Integer status) {}
}
