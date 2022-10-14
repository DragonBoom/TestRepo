package indi.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

@SpringBootTest
@ActiveProfiles({"secret", "rabbit"})
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
public class RabbitmqTest {
    @Autowired
    private ConfigurableApplicationContext cac;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    
    /**
     * 测试结合Spring Boot使用
     * 
     * <p>SpringBoot配置类为：RabbitProperties
     */
    @Test
    void withSpringBootTest() {
        System.out.println(rabbitTemplate);
        // rabbitTemplate.receiveAndConvert() 似乎会因没有配置默认接收的队列而报错，而且确实没有配置项可以对此进行配置
//        Object result = rabbitTemplate.receiveAndConvert();
//        System.out.println(result);
        // use default routing key
        /*
         * 下一行，若没配置spring.rabbitmq.template.routing-key会抛异常：
         * org.springframework.amqp.AmqpIllegalStateException: No 'queue' specified. Check configuration of RabbitTemplate.
         */
        rabbitTemplate.convertAndSend("hello");
        rabbitTemplate.convertAndSend("hello2");
        rabbitTemplate.convertAndSend("hello3");
        
        System.out.println(rabbitTemplate.receiveAndConvert("queue"));
        System.out.println(rabbitTemplate.receiveAndConvert("queue"));
        System.out.println(rabbitTemplate.receiveAndConvert("queue"));
        
        rabbitTemplate.convertAndSend("hello2");
    }
}
