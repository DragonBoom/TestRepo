package indi.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import indi.rabbitmq.RabbitConfig;
import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

@ExtendWith(TestSeparateExtension.class)
class SpringContextPlainText {
    
    

    /**
     * 测试直接加载SpringContext（Spring容器）：利用AnnotationConfigApplicationContext，
     * 加载配置类，用配置类的@PropertySource声明加载配置文件，进而生成取自配置文件的context
     * 
     * @since 2022-10-13
     */
    @Test
    void loadRabbitContext() {
        // 尝试加载配置类，通过配置类的注解读取@PropertySource配置文件
        AnnotationConfigApplicationContext rabbitCtx = new AnnotationConfigApplicationContext();
        rabbitCtx.register(RabbitConfig.class);
        rabbitCtx.refresh();
        System.out.println(rabbitCtx.getEnvironment().getProperty("spring.rabbitmq.username"));
        rabbitCtx.close();
    }
    
    /**
     * 尝试模仿@PropertySource的源码读取配置文件，这样就不需要借助SpringContext了
     * 
     * @since 2022-10-13
     */
    @Test
    @SneakyThrows
    void loadRabbitContextByPropertySourceFactory() {
        DefaultPropertySourceFactory factory = new DefaultPropertySourceFactory();
        PropertySource<?> propertySource = factory.createPropertySource(null,
                new EncodedResource(new ClassPathResource("application-rabbit.properties")));
        System.out.println(propertySource.getProperty("spring.rabbitmq.username"));
    }
}
