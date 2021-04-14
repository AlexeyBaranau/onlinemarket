package by.finalproject.controller.request.basket;

import lombok.Data;

@Data
public class BasketItemRequest {

    private Long productId;

    private Integer amount;
}
