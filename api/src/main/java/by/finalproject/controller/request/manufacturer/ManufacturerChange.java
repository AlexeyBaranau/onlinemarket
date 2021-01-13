package by.finalproject.controller.request.manufacturer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManufacturerChange extends ManufacturerCreate {
  private Long id;
}
