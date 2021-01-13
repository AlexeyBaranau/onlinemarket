package by.finalproject.service.impl;

import by.finalproject.domain.Order;
import by.finalproject.service.OrderService;
import org.springframework.data.domain.Page;

public class OrderServiceImpl implements OrderService {
    @Override
    public Integer getAllOrdersCount() {
        return null;
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
