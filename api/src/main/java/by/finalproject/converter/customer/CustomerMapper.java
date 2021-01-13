package by.finalproject.converter.customer;

import by.finalproject.controller.request.customer.CustomerChange;
import by.finalproject.controller.request.customer.CustomerCreate;
import by.finalproject.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {

  @Mappings({@Mapping(target = "id", ignore = true)})
  public abstract Customer customerDTOtoCustomer(CustomerCreate customerCreate);

  public abstract Customer customerDTOtoCustomer(
      CustomerChange customerChange, @MappingTarget Customer customer);
}
