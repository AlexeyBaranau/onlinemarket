package by.finalproject.converter.order;

import by.finalproject.controller.request.order.OrderDTO;
import by.finalproject.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import static org.mapstruct.CollectionMappingStrategy.ADDER_PREFERRED;

@Mapper(componentModel = "spring", collectionMappingStrategy = ADDER_PREFERRED)
public interface OrderMapper {

  @Mappings({
    @Mapping(target = "created", source = "created"),
    @Mapping(target = "totalPrice", source = "totalPrice"),
    @Mapping(target = "status", source = "status"),
    @Mapping(target = "orderedProducts", source = "orderProductList")
  })
  OrderDTO toDTO(Order order);
}
