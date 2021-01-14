package by.finalproject.service;

import by.finalproject.domain.Order;

import java.util.List;

public interface OrderService {

    Order addToOrder(Long productId, Integer amount);

    List<Order> getOrders();

    List<Order> getAllOrders();
}
