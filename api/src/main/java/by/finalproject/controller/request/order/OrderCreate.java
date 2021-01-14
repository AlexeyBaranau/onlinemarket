package by.finalproject.controller.request.order;

import lombok.Data;

@Data
public class OrderCreate {

  private Long productId;

  private Integer amount;
}
