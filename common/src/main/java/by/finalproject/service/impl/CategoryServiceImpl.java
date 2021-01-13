package by.finalproject.service.impl;

import by.finalproject.domain.Category;
import by.finalproject.exception.EntityNotFoundException;
import by.finalproject.repository.CategoryRepository;
import by.finalproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category findById(Long categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new EntityNotFoundException("No found category by id " + categoryId));
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public Category create(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public Category update(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void delete(Long categoryId) {
    categoryRepository.deleteById(categoryId);
  }
}
