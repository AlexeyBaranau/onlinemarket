package by.finalproject.converter.category;

import by.finalproject.controller.request.category.CategoryChange;
import by.finalproject.controller.request.category.CategoryCreate;
import by.finalproject.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

  @Mappings({@Mapping(target = "id", ignore = true), @Mapping(target = "name", source = "name")})
  public abstract Category categoryDTOtoCategory(CategoryCreate categoryCreate);

  @Mappings({@Mapping(target = "id", source = "id"), @Mapping(target = "name", source = "name")})
  public abstract Category categoryDTOtoCategory(CategoryChange categoryChange);
}
