package by.finalproject.service.impl;

import by.finalproject.domain.Customer;
import by.finalproject.domain.Order;
import by.finalproject.repository.OrderRepository;
import by.finalproject.service.BasketService;
import by.finalproject.service.CustomerService;
import by.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    return null;
  }

  @Override
  public Page<Order> findAllOrders(String number, Integer page, Integer pageSize) {
    return null;
  }

  @Override
  public Order saveOrder() {
    return null;
  }

  @Override
  public void changeOrderStatus(String code, Integer status) {

  }
}
