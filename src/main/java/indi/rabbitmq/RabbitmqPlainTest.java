package indi.rabbitmq;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.env.PropertySource;

import indi.util.SpringUtils;

class RabbitmqPlainTest {
    static String rabbitHost;
    static int rabbitPort;
    static String rabbitUsername;
    static String rabbitPassword;
    
    @BeforeAll
    static void init() {
        PropertySource<?> propertySource = SpringUtils.getPropertySource("application-secret.properties");
        // 从Spring配置文件读取配置（application-rabbit.properties）
        rabbitUsername = (String) propertySource.getProperty("vps1.rabbitmq.username");
        rabbitPassword = (String) propertySource.getProperty("vps1.rabbitmq.password");
        rabbitHost = (String) propertySource.getProperty("vps1.rabbitmq.host");
        rabbitPort = Integer.parseInt((String) propertySource.getProperty("vps1.rabbitmq.port"));

    }

    /**
     * 第一步，测试不借助Spring容器(context)建立连接并发、收消息
     */
    @Test
    void go() {
        // 手动创建Spring Context（Spring容器）
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost, rabbitPort);
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPassword);

        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        String declareQueue = admin.declareQueue(new Queue("queue"));
        System.out.println(declareQueue);
        
        AmqpTemplate template = new RabbitTemplate(connectionFactory);
        
        template.convertAndSend("queue", "foo");
        String foo = (String) template.receiveAndConvert("queue");
        
        System.out.println(foo);
        Assertions.assertEquals("foo", foo);
    }
}
