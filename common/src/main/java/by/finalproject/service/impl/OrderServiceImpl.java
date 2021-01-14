package by.finalproject.service.impl;

import by.finalproject.domain.Customer;
import by.finalproject.domain.Order;
import by.finalproject.domain.Product;
import by.finalproject.repository.OrderRepository;
import by.finalproject.service.CustomerService;
import by.finalproject.service.OrderService;
import by.finalproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final ProductService productService;
  private final OrderRepository orderRepository;
  private final CustomerService customerService;

  @Override
  public Order addToOrder(Long productId, Integer amount) {
    Product product = productService.findById(productId);
    Customer customer = customerService.getCustomer();

    Order order = new Order();
    order.setAmount(amount);
    order.setCreated(new Timestamp(System.currentTimeMillis()));
    order.setCustomer(customer.getId());
    order.setProductId(product.getId());
    order.setTotalPrice(product.getPrice() * amount);
    return orderRepository.save(order);
  }

  @Override
  public List<Order> getOrders() {
    Customer customer = customerService.getCustomer();
    return orderRepository.findAllByCustomer(customer.getId());
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }
}
