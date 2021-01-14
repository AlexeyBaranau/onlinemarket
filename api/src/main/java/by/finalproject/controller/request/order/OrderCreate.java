package by.finalproject.controller.request.order;

import lombok.Data;

@Data
public class OrderCreate {

  private Double totalPrice;

  private Long productId;

  private Integer amount;

  private Long customerId;
}
