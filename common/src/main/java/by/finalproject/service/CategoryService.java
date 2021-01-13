package by.finalproject.service;

import by.finalproject.domain.Category;

import java.util.List;

public interface CategoryService {

  Category findById(Long categoryId);

  List<Category> findAll();

  Category create(Category category);

  Category update(Category category);

  void delete(Long categoryId);
}
