package by.finalproject.controller.request.order;

import lombok.Data;

@Data
public class OrderCreate {
  private Long productId;

  private Long customerId;

  private Double totalPrice;

  private int amount;
}
