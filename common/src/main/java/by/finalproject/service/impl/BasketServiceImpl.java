package by.finalproject.service.impl;

import by.finalproject.domain.Basket;
import by.finalproject.domain.BasketItem;
import by.finalproject.domain.Customer;
import by.finalproject.domain.Product;
import by.finalproject.exception.EntityNotFoundException;
import by.finalproject.repository.BasketRepository;
import by.finalproject.service.BasketService;
import by.finalproject.service.CustomerService;
import by.finalproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

  private final BasketRepository basketRepository;
  private final ProductService productService;
  private final CustomerService customerService;

  @Override
  public Basket getBasket() {
    Customer customer = customerService.getCustomer();
    Basket basket = customer.getBasket();
    if (isNull(basket)) {
      basket = createBasket(customer);
      customer.setBasket(basket);
      basketRepository.save(basket);
    }
    return basket;
  }

  @Override
  public Basket addToBasket(Long productId, Integer amount) {
    Basket basket = getBasket();
    if (nonNull(basket.getBasketItemList()) && !basket.getBasketItemList().isEmpty()) {
      final Optional<BasketItem> basketItem =
          basket.getBasketItemList().stream()
              .filter(bi -> bi.getProduct().getId().equals(productId))
              .findFirst();

      if (basketItem.isPresent()) {
        basketItem.get().setAmount(basketItem.get().getAmount() + amount);
        final Basket updatedBasket = calculateTotalPrice(basket);
        basket = basketRepository.save(updatedBasket);
        return basket;
      }
    }
    Product product = productService.findById(productId);
    BasketItem basketItem =
        BasketItem.builder().basket(basket).amount(amount).product(product).build();

    basket.getBasketItemList().add(basketItem);
    basket = basketRepository.save(calculateTotalPrice(basket));
    return basket;
  }

  @Override
  public Basket increaseBasketItem(Long productId, Integer amount) {
    Basket basket = getBasket();

    BasketItem basketItem =
        basket.getBasketItemList().stream()
            .filter(bi -> bi.getProduct().getId().equals(productId))
            .findFirst()
            .orElseThrow(
                () ->
                    new EntityNotFoundException(format("Product with id %d not found", productId)));

    basketItem.setAmount(basketItem.getAmount() + amount);
    basket = basketRepository.save(calculateTotalPrice(basket));
    return basket;
  }

  @Override
  public Basket decreaseBasketItem(Long productId, Integer amount) {
    Basket basket = getBasket();

    BasketItem basketItem =
        basket.getBasketItemList().stream()
            .filter(bi -> bi.getProduct().getId().equals(productId))
            .findFirst()
            .orElseThrow(
                () ->
                    new EntityNotFoundException(format("Product with id %d not found", productId)));

    if (basketItem.getAmount() <= 0) {
      basket.getBasketItemList().remove(basketItem);
      basket = basketRepository.save(calculateTotalPrice(basket));
      return basket;
    } else {
      basketItem.setAmount(basketItem.getAmount() - amount);
      basket = basketRepository.save(calculateTotalPrice(basket));
      return basket;
    }
  }

  @Override
  public Basket removeBasketItem(Long productId) {
    Basket basket = getBasket();

    BasketItem basketItem =
        basket.getBasketItemList().stream()
            .filter(bi -> bi.getProduct().getId().equals(productId))
            .findFirst()
            .orElseThrow(
                () ->
                    new EntityNotFoundException(format("Product with id %d not found", productId)));
    basket.getBasketItemList().remove(basketItem);
    if (basket.getBasketItemList().isEmpty() || basket.getBasketItemList().size() == 0) {
      basket.setTotalPrice(null);
      return basket;
    }
    basket = basketRepository.save(calculateTotalPrice(basket));
    return basket;
  }

  @Override
  public void clearBasket() {
    Basket basket = getBasket();
    basket.getBasketItemList().clear();
    basket.setTotalPrice(null);
    basketRepository.save(basket);
  }

  private Basket createBasket(Customer customer) {
    return Basket.builder().customer(customer).basketItemList(new ArrayList<>()).build();
  }

  private Basket calculateTotalPrice(Basket basket) {
    basket.setTotalPrice(ZERO);
    basket
        .getBasketItemList()
        .forEach(
            basketItem ->
                basket.setTotalPrice(
                    basket
                        .getTotalPrice()
                        .add(
                            basketItem
                                .getProduct()
                                .getPrice()
                                .multiply(new BigDecimal(basketItem.getAmount())))));
    return basket;
  }
}
