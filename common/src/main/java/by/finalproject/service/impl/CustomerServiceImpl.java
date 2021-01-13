package by.finalproject.service.impl;

import by.finalproject.domain.Customer;
import by.finalproject.domain.Role;
import by.finalproject.domain.SystemRoles;
import by.finalproject.exception.EntityNotFoundException;
import by.finalproject.repository.CustomerRepository;
import by.finalproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Customer getCustomer() {
    String login = SecurityContextHolder.getContext().getAuthentication().getName();
    log.info(login);
    if (login.isEmpty() | login.equals("annonimus")) {
      throw new AccessDeniedException("Access Denied");
    }
    return customerRepository
        .findByLoginIgnoreCase(login)
        .orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Customer create(Customer customer) {
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    Role role = new Role();
    role.setRoleName(SystemRoles.ROLE_USER);
    role.setCustomer(customer);
    customer.setRoles(Collections.singleton(role));
    return customerRepository.save(customer);
  }

  @Override
  public Customer update(Customer customer) {
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    customer.setChanged(new Timestamp(System.currentTimeMillis()));
    return customerRepository.save(customer);
  }

  @Override
  public void delete(Long id) {
    customerRepository.deleteById(id);
  }
}
