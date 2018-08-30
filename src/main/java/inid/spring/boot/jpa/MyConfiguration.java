package inid.spring.boot.jpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories
public class MyConfiguration {
    @Autowired
    DataSource dataSrouce;

    @Bean
    public CommandLineRunner run() {
        return (strings) -> {
            System.out.println(dataSrouce);
        };
    }

    // @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("");
        // ...
        return null;
    }

}
