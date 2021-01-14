package by.finalproject.controller;

import by.finalproject.controller.request.order.OrderCreate;
import by.finalproject.domain.Order;
import by.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Order> addProductToOrder(@RequestBody OrderCreate orderCreate) {
    Order order = orderService.addToOrder(orderCreate.getProductId(), orderCreate.getAmount());
    return new ResponseEntity<>(order, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/all")
  public ResponseEntity<List<Order>> getAll() {
    return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
  }
}
