package by.finalproject.converter.product;

import by.finalproject.controller.request.product.ProductChange;
import by.finalproject.controller.request.product.ProductCreate;
import by.finalproject.domain.Product;
import by.finalproject.service.CategoryService;
import by.finalproject.service.ManufacturerService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

  @Autowired protected ManufacturerService manufacturerService;
  @Autowired protected CategoryService categoryService;

  @AfterMapping
  protected void setInfo(@MappingTarget Product product, ProductCreate productCreate) {
    product.setManufacturer(manufacturerService.findById(productCreate.getManufacturerId()));
    product.setCategory(categoryService.findById(productCreate.getCategoryId()));
  }

  @Mappings({
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "description", source = "description"),
    @Mapping(target = "name", source = "name"),
    @Mapping(target = "price", source = "price")
  })
  public abstract Product productDTOtoProduct(ProductCreate productCreate);

  @Mappings({
    @Mapping(target = "id", source = "id"),
    @Mapping(target = "description", source = "description"),
    @Mapping(target = "name", source = "name"),
    @Mapping(target = "price", source = "price")
  })
  public abstract Product updateProductDTOtoProduct(
      ProductChange productChange, @MappingTarget Product product);
}
