package by.finalproject.controller.request.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderedProduct {

  private Long id;

  private BigDecimal price;

  private Integer amount;
}
