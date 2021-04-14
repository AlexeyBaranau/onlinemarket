package by.finalproject.controller;

import by.finalproject.controller.request.basket.BasketDTO;
import by.finalproject.controller.request.basket.BasketItemRequest;
import by.finalproject.converter.basket.BasketMapper;
import by.finalproject.domain.Basket;
import by.finalproject.service.BasketService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/baskets")
public class BasketController {

  private final BasketService basketService;
  private final BasketMapper basketMapper;

  @ApiOperation("Get basket")
  @GetMapping
  public ResponseEntity<BasketDTO> getBasket() {
    Basket basket = basketService.getBasket();
    return ResponseEntity.ok(basketMapper.toDTO(basket));
  }

  @ApiOperation("Add product to basket")
  @PostMapping
  public ResponseEntity<BasketDTO> addToBasket (@RequestBody BasketItemRequest basketItemRequest){
  Basket basket = basketService.addToBasket(basketItemRequest.getProductId(), basketItemRequest.getAmount());
  return ResponseEntity.ok(basketMapper.toDTO(basket));
  }

  @ApiOperation("Increase item quantity")
  @PostMapping("/increase")
  public ResponseEntity<BasketDTO> increaseBasketItem (@RequestBody BasketItemRequest basketItemRequest){
  Basket basket = basketService.increaseBasketItem(basketItemRequest.getProductId(), basketItemRequest.getAmount());
  return ResponseEntity.ok(basketMapper.toDTO(basket));
  }

  @ApiOperation("Decrease item quantity")
  @PostMapping("/decrease")
  public ResponseEntity<BasketDTO> decreaseBasketItem (@RequestBody BasketItemRequest basketItemRequest){
  Basket basket = basketService.decreaseBasketItem(basketItemRequest.getProductId(), basketItemRequest.getAmount());
  return ResponseEntity.ok(basketMapper.toDTO(basket));
  }

  @ApiOperation("Remove item from basket")
  @DeleteMapping("/items/{item_id}")
  public ResponseEntity<BasketDTO> removeBasketItem (@PathVariable("item_id") Long id){
  Basket basket = basketService.removeBasketItem(id);
  return ResponseEntity.ok(basketMapper.toDTO(basket));
  }

  @ApiOperation("Clear basket")
  @DeleteMapping
  public ResponseEntity<BasketDTO> clearBasket (){
  basketService.clearBasket();
  return ResponseEntity.ok().build();
  }




}
