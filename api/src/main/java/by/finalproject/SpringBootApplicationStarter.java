package by.finalproject;

import by.finalproject.config.ApplicationBeans;
import by.finalproject.config.PersistenceContextBeansConfiguration;
import by.finalproject.security.configuration.WebSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "by.finalproject")
@Import({
  ApplicationBeans.class,
  PersistenceContextBeansConfiguration.class,
  WebSecurityConfiguration.class
})
public class SpringBootApplicationStarter {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootApplicationStarter.class, args);
  }
}

/*DOCKER*/