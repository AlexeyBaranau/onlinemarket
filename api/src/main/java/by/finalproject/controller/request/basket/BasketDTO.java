package by.finalproject.controller.request.basket;

import by.finalproject.controller.request.order.OrderedProduct;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BasketDTO {

    private Long id;
    
    private BigDecimal totalPrice;

    private List<OrderedProduct> basketItems;
}
