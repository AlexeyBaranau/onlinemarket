package by.finalproject.converter.manufacturer;

import by.finalproject.controller.request.manufacturer.ManufacturerChange;
import by.finalproject.controller.request.manufacturer.ManufacturerCreate;
import by.finalproject.domain.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class ManufacturerMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "name", source = "name")})
  public abstract Manufacturer manufacturerDTOtoManufacturer(ManufacturerCreate manufacturerCreate);

  public abstract Manufacturer manufacturerDTOtoManufacturer(ManufacturerChange manufacturerChange, @MappingTarget Manufacturer manufacturer);
}
