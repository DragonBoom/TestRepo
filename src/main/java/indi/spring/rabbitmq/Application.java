package indi.spring.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        // 打印可变变量？
        MutablePropertySources propertySources = ctx.getEnvironment().getPropertySources();

        propertySources.forEach(source -> {
            System.out.println(source);
            System.out.println(source.getSource());
        });
    }
    
    

}
