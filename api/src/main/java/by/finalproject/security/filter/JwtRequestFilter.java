package by.finalproject.security.filter;

import by.finalproject.repository.CustomerRepository;
import by.finalproject.security.util.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  private final CustomerRepository customerRepository;
  private final UserDetailsService userServiceProvider;
  private final TokenUtils tokenUtils;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    final String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

    String login = null;

    if (nonNull(jwt)) {
      login = tokenUtils.getUsernameFromToken(jwt);
    }

    if (nonNull(login) && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails customer = userServiceProvider.loadUserByUsername(login);
      if (tokenUtils.validateToken(jwt, customer)) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
