package by.finalproject.service;

import by.finalproject.domain.Basket;

public interface BasketService {
    Basket getBasket();

    Basket addToBasket(Long id, Integer amount);

    Basket increaseBasketItem(Long id, Integer amount);

    Basket decreaseBasketItem(Long id, Integer amount);

    Basket removeBasketItem(Long id);

    void clearBasket();
}
