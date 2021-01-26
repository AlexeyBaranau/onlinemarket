package by.finalproject.service;

import by.finalproject.domain.Customer;

public interface CustomerService {

  Customer getCustomer();

  Customer findById(Long id);

  Customer create(Customer customer);

  Customer update(Customer customer);

  void delete(Long id);

  Customer deleteBySettingIsDeletedTrue(Long id);
}
