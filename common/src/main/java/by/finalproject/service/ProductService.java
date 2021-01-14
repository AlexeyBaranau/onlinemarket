package by.finalproject.service;

import by.finalproject.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

  Product findById(Long productId);

  Page<Product> findAll(Integer page, Integer pageSize);

  Page<Product> findAllManufacturersProducts(Long manufacturerId, Integer page, Integer pageSize);

  Page<Product> findAllCategoryProducts(Long categoryId, Integer page, Integer pageSize);

  Product create(Product product);

  Product update(Product product);

  void delete(Long productId);
}
