package by.finalproject.controller;

import by.finalproject.controller.request.order.OrderDTO;
import by.finalproject.controller.request.order.UpdateOrderStatus;
import by.finalproject.converter.order.OrderMapper;
import by.finalproject.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  @ApiOperation("Make order")
  @PostMapping
  public ResponseEntity<OrderDTO> makeOrder() {
    return ResponseEntity.ok(orderMapper.toDTO(orderService.saveOrder()));
  }

  @ApiOperation("Order list")
  @GetMapping
  public ResponseEntity<Page<OrderDTO>> getAllCustomerOrders(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize) {
    Page<OrderDTO> orders =
        orderService.findAllCustomerOrders(page, pageSize).map(orderMapper::toDTO);
    return ResponseEntity.ok(orders);
  }

  @ApiOperation("Count of orders")
  @GetMapping("/count")
  public ResponseEntity<Integer> getAllOrdersCount() {
    final Integer orderCount = orderService.getAllOrdersCount();
    return ok(orderCount);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation("List of all orders")
  @PutMapping("/all")
  public ResponseEntity<Page<OrderDTO>> changeStatus(@RequestBody UpdateOrderStatus request){
    orderService.changeOrderStatus(request.getId(), request.getStatus());
    return ResponseEntity.ok().build();
  }

}
