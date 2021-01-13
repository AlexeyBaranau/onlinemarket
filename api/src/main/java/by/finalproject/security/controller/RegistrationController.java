package by.finalproject.security.controller;

import by.finalproject.controller.request.customer.CustomerCreate;
import by.finalproject.converter.customer.CustomerMapper;
import by.finalproject.domain.Customer;
import by.finalproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

  private final CustomerService customerService;
  private final CustomerMapper customerMapper;

  @PostMapping
  public ResponseEntity<Customer> registration(@RequestBody CustomerCreate customerCreate) {
    Customer customer = customerMapper.customerDTOtoCustomer(customerCreate);
    return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
  }
}
