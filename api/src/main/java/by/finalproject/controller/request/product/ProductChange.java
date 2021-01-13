package by.finalproject.controller.request.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductChange extends ProductCreate {
  private Long id;
}
