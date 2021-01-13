package by.finalproject.controller.request.product;

import lombok.Data;

@Data
public class ProductCreate {

  private String name;

  private String description;

  private Double price;

  private Long categoryId;

  private Long manufacturerId;
}
