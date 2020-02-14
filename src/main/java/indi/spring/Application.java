package indi.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("start at: " + Application.class.getName());// well...
        SpringApplication.run(Application.class, args);
        
    }
}
