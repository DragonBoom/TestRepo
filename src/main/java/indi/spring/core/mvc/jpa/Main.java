package indi.spring.core.mvc.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {
        // org.activiti.spring.boot.DataSourceProcessEngineAutoConfiguration.class,
        // org.activiti.spring.boot.EndpointAutoConfiguration.class,
        // org.activiti.spring.boot.RestApiAutoConfiguration.class,
        // org.activiti.spring.boot.JpaProcessEngineAutoConfiguration.class,
        // org.activiti.spring.boot.SecurityAutoConfiguration.class
})
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
