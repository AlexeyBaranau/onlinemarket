package by.finalproject.controller;

import by.finalproject.controller.request.category.CategoryChange;
import by.finalproject.controller.request.category.CategoryCreate;
import by.finalproject.converter.category.CategoryMapper;
import by.finalproject.domain.Category;
import by.finalproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;
  private final CategoryMapper categoryMapper;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> findById(@PathVariable Long id) {
    return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Category> categoryCreate(@RequestBody CategoryCreate categoryCreate) {
    Category category = categoryMapper.categoryDTOtoCategory(categoryCreate);
    return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping
  public ResponseEntity<Category> categoryUpdate(@RequestBody CategoryChange categoryChange) {
    Category category = categoryMapper.categoryDTOtoCategory(categoryChange);
    return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Category> categoryDelete(@PathVariable Long id) {
    categoryService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
