package indi.spring.rabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
public class RabbitmqTest {

    /**
     * 第一步，测试连接，发、收消息
     */
    @Test
    void go() {
        // CachingConnectionFactory connectionFactory = new CachingConnectionFactory(); // 默认只能连接本地测试账号？
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("172.104.66.71");
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("test");

        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue("queue"));
        
        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        
        template.convertAndSend("queue", "foo");
        String foo = (String) template.receiveAndConvert("queue");
        System.out.println(foo);
    }
}
