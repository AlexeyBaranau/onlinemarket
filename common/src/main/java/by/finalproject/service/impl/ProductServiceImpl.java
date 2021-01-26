package by.finalproject.service.impl;

import by.finalproject.domain.Category;
import by.finalproject.domain.Manufacturer;
import by.finalproject.domain.Product;
import by.finalproject.exception.EntityNotFoundException;
import by.finalproject.repository.ProductRepository;
import by.finalproject.service.CategoryService;
import by.finalproject.service.ManufacturerService;
import by.finalproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ManufacturerService manufacturerService;
  private final CategoryService categoryService;

  @Override
  public Product findById(Long productId) {
    return productRepository
        .findById(productId)
        .orElseThrow(() -> new EntityNotFoundException("Not found product with id " + productId));
  }

  @Override
  public Page<Product> findAll(Integer page, Integer pageSize) {
    return productRepository.findAll(PageRequest.of(page, pageSize));
  }


  @Override
  public Page<Product> findAllManufacturersProducts(
      Long manufacturerId, Integer page, Integer pageSize) {
    Manufacturer manufacturer = manufacturerService.findById(manufacturerId);
    return productRepository.findAllByManufacturer(manufacturer, PageRequest.of(page, pageSize));
  }

  @Override
  public Page<Product> findAllCategoryProducts(Long categoryId, Integer page, Integer pageSize) {
    Category category = categoryService.findById(categoryId);
    return productRepository.findAllByCategory(category, PageRequest.of(page, pageSize));
  }

  @Override
  public Product create(Product product) {
    product.setChanged(new Timestamp(System.currentTimeMillis()));
    product.setCreated(new Timestamp(System.currentTimeMillis()));
    product.setDeleted(false);
    return productRepository.save(product);
  }

  @Override
  public Product update(Product product) {
    product.setChanged(new Timestamp(System.currentTimeMillis()));
    return productRepository.save(product);
  }

  @Override
  public void delete(Long productId) {
    productRepository.deleteById(productId);
  }
}

