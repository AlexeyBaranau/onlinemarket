package by.finalproject.converter.basket;

import by.finalproject.controller.request.basket.BasketDTO;
import by.finalproject.domain.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BasketMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "totalPrice", source = "totalPrice"),
            @Mapping(target = "basketItems", source = "basketItemList")
    })
    BasketDTO toDTO(Basket basket);
    }