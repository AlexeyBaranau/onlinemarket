package by.finalproject.controller.request.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerChange extends CustomerCreate {
  private Long id;
}
