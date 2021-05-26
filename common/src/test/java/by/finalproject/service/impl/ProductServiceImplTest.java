package by.finalproject.service.impl;

import by.finalproject.domain.Category;
import by.finalproject.domain.Manufacturer;
import by.finalproject.domain.Product;
import by.finalproject.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;


    @Test
    void findById() {
        Long id = 1L;
        var product = Product.builder().id(id).description("product").build();
        var expected = Product.builder().id(id).description("product").build();
        given(productRepository.findById(id)).willReturn(Optional.of(product));

        final var actual = productService.findById(id);
        assertThat(actual, is(expected));

        then(productRepository).should(only()).findById(argThat(id::equals));
    }

    @Test
    void findAll() {
        List<Product> products =
                List.of(new Product(1L, "Iphone", "Mobile telefon", new BigDecimal(540), new Timestamp(1), new Timestamp(1), false, new Manufacturer(1L, "name"), new Category()));
        List<Product> expected = List.of(new Product(1L, "Iphone", "Mobile telefon", new BigDecimal(540), new Timestamp(1), new Timestamp(1), false, new Manufacturer(1L, "name"), new Category()));

        given(productRepository.findAll()).willReturn(products);

        assertThat(products, is(expected));

    }

}