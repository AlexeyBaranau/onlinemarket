package by.finalproject.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

public class PersistenceContextBeansConfiguration {


    private Properties getAdditionalProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.show_sql", "true");
        properties.put("current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");

        return properties;
    }
}
