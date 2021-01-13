package by.finalproject.controller;

import by.finalproject.controller.request.customer.CustomerChange;
import by.finalproject.converter.customer.CustomerMapper;
import by.finalproject.domain.Customer;
import by.finalproject.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

  public final CustomerService customerService;
  public final CustomerMapper customerMapper;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Customer getInformation() {
    return customerService.getCustomer();
  }

  @PutMapping
  public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerChange customerChange) {
    Customer c = customerService.getCustomer();
    Customer customer = customerMapper.customerDTOtoCustomer(customerChange, c);
    return new ResponseEntity<>(customerService.update(customer), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete customer by id")
  @DeleteMapping("/{customer_id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public HttpStatus deleteById(@PathVariable("customer_id") Long id) {
    customerService.delete(id);
    return HttpStatus.NO_CONTENT;
  }
}
