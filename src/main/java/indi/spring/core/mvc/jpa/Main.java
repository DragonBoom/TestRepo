package indi.spring.core.mvc.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

<<<<<<< HEAD
@SpringBootApplication(exclude = {
        // org.activiti.spring.boot.DataSourceProcessEngineAutoConfiguration.class,
        // org.activiti.spring.boot.EndpointAutoConfiguration.class,
        // org.activiti.spring.boot.RestApiAutoConfiguration.class,
        // org.activiti.spring.boot.JpaProcessEngineAutoConfiguration.class,
        // org.activiti.spring.boot.SecurityAutoConfiguration.class
})
=======
@SpringBootApplication
>>>>>>> 1d5fdfd4b1b845f2b4fb31228d2ecd9284eff170
@EnableJpaRepositories
@EnableAspectJAutoProxy
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
