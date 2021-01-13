package by.finalproject.security.service;

import by.finalproject.domain.Customer;
import by.finalproject.domain.Role;
import by.finalproject.domain.SystemRoles;
import by.finalproject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceProvider implements UserDetailsService {

  private final CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Optional<Customer> searchResult = customerRepository.findByLoginIgnoreCase(username);
      if (searchResult.isPresent()) {
        Customer customer = searchResult.get();
        return new User(
            customer.getLogin(),
            customer.getPassword(),
            AuthorityUtils.commaSeparatedStringToAuthorityList(
                customer.getRoles().stream()
                    .map(Role::getRoleName)
                    .map(SystemRoles::name)
                    .collect(Collectors.joining(","))));
      } else {
        throw new UsernameNotFoundException(
            String.format("No user found with login '%s'.", username));
      }
    } catch (Exception e) {
      throw new UsernameNotFoundException("User with this login not found");
    }
  }
}
