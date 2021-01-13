package by.finalproject.controller.request.category;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryChange extends CategoryCreate {
  private Long id;
}
