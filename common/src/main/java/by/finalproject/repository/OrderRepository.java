package by.finalproject.repository;

import by.finalproject.domain.Customer;
import by.finalproject.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Integer> countAllByCustomer(Customer customer);

    Page<Order> findAllByCustomerOrderByDateDesc(Customer customer, Pageable pageable);

    List<Order> findAllByCustomer(Long id);
}
