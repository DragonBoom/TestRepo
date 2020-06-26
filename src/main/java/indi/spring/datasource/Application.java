package indi.spring.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        System.out.println("start at: " + Application.class.getName());// useless...
        SpringApplication.run(Application.class, args);
        
    }
}
