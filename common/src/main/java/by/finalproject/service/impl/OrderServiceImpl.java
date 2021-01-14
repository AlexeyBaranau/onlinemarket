package by.finalproject.service.impl;

import by.finalproject.domain.Order;
import by.finalproject.domain.Product;
import by.finalproject.service.OrderService;
import by.finalproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;

    @Override
    public Order addToOrder(Long productId, Integer amount) {
        Product product = productService.findById(productId);
        Double price = product.getPrice();

        Order order = Order.builder().created(new Timestamp(System.currentTimeMillis())).totalPrice(price*amount).build();
        return order;
    }
}
