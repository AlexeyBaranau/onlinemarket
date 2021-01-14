package by.finalproject.converter.order;

import by.finalproject.controller.request.order.OrderCreate;
import by.finalproject.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "totalPrice", source = "totalPrice"),
            @Mapping(target = "productId", source = "productId"),
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "customerId", source = "customerId"),
    })
    public abstract Order orderDTOtoOrder(OrderCreate orderCreate);

}