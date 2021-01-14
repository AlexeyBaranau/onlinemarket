package by.finalproject.controller;

import by.finalproject.converter.order.OrderMapper;
import by.finalproject.domain.Order;
import by.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderMapper orderMapper;
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Order> addProductToOrder(
      @PathVariable Long productId,
      @PathVariable Integer amount) {
    Order order = orderService.addToOrder(productId, amount);
    return new ResponseEntity<>(order, HttpStatus.OK);
  }
}
