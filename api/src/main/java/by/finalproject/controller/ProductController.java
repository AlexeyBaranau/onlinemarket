package by.finalproject.controller;

import by.finalproject.controller.request.product.ProductChange;
import by.finalproject.controller.request.product.ProductCreate;
import by.finalproject.converter.product.ProductMapper;
import by.finalproject.domain.Product;
import by.finalproject.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;

  @ApiOperation(value = "Get all products")
  @GetMapping
  public ResponseEntity<Page<Product>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
    return new ResponseEntity<>(productService.findAll(page, pageSize), HttpStatus.OK);
  }

  @ApiOperation(value = "Get all product by manufacturer id")
  @GetMapping("/manufacturer")
  public ResponseEntity<Page<Product>> findAllManufacturersProducts(
      @RequestParam(value = "id", required = false) Long manufacturerId,
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
    return new ResponseEntity<>(
        productService.findAllManufacturersProducts(manufacturerId, page, pageSize), HttpStatus.OK);
  }

  @ApiOperation(value = "Get all product by category id")
  @GetMapping("/category")
  public ResponseEntity<Page<Product>> findAllCategoryProducts(
      @RequestParam(value = "id", required = false) Long categoryId,
      @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
    return new ResponseEntity<>(
        productService.findAllCategoryProducts(categoryId, page, pageSize), HttpStatus.OK);
  }

  @ApiOperation(value = "Get product by id")
  @GetMapping("/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable Long id) {
    return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Create product")
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody ProductCreate productCreate) {
    Product product = productMapper.productDTOtoProduct(productCreate);
    return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Update product")
  @PutMapping
  public ResponseEntity<Product> createProduct(@RequestBody ProductChange productChange) {
    Product product =
        productMapper.updateProductDTOtoProduct(
            productChange, productService.findById(productChange.getId()));
    return new ResponseEntity<>(productService.update(product), HttpStatus.CREATED);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete product by id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Product> createProduct(@PathVariable Long id) {
    productService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
