package by.finalproject.service;

import by.finalproject.domain.Order;

public interface OrderService {

    Order addToOrder(Long productId, Integer amount);

}
