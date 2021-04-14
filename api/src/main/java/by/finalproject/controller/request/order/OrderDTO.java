package by.finalproject.controller.request.order;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDTO {

  private Timestamp created;

  private BigDecimal totalPrice;

  private String status;

  private List<OrderedProduct> orderedProducts;
}
