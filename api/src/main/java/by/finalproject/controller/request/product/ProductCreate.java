package by.finalproject.controller.request.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreate {

  private String name;

  private String description;

  private BigDecimal price;

  private Long categoryId;

  private Long manufacturerId;
}
