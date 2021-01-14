package by.finalproject.repository;

import by.finalproject.domain.Category;
import by.finalproject.domain.Manufacturer;
import by.finalproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Page<Product> findAllByManufacturer(Manufacturer manufacturer, Pageable pageable);

  Page<Product> findAllByCategory(Category category, Pageable pageable);

}
