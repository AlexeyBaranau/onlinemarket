package by.finalproject.security.configuration;

import by.finalproject.security.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userServiceProvider;
  private final PasswordEncoder passwordEncoder;
  private final JwtRequestFilter jwtRequestFilter;

  @Autowired
  public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {

    authenticationManagerBuilder
        .userDetailsService(userServiceProvider)
        .passwordEncoder(passwordEncoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable();
    httpSecurity
        .authorizeRequests(
            authorize ->
                authorize
                    .antMatchers(HttpMethod.GET, "/api/v1/manufacturers/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/products/**")
                    .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/categories/**")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/api/v1/registration")
                    .permitAll()
                    .antMatchers(HttpMethod.POST, "/api/v1/authentication")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  // For swagger access only
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers(
            "/v3/api-docs",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/static/**",
            "/templates/**");
  }
}
