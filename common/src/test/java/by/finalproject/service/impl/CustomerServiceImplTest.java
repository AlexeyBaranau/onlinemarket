package by.finalproject.service.impl;

import by.finalproject.domain.Customer;
import by.finalproject.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

    @InjectMocks private CustomerServiceImpl customerService;
    @Mock private CustomerRepository customerRepository;


    @Test
    void findById() {
        var id = 1L;
        var expected =
                Customer.builder().name("John").surname("Snow").address("Street").build();
        given(customerRepository.findById(id)).willReturn(Optional.of(expected));
        var actual = customerService.findById(id);
        assertThat(actual, is(expected));
        then(customerRepository).should(only()).findById(eq(id));
    }

}