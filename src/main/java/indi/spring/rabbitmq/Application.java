package indi.spring.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;

@SpringBootApplication
//@PropertySource("classpath:spring-rabbit.properties")
public class Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "rabbit");// 设置临时环境变量，指定配置文件 application-rabbit.properties
        
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        MutablePropertySources propertySources = ctx.getEnvironment().getPropertySources();

        propertySources.forEach(source -> {
            System.out.println(source);
            System.out.println(source.getSource());
        });
    }
    
    

}
