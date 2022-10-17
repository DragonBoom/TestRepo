package indi.rabbitmq;

import indi.util.extension.TestSeparateExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles({"secret", "rabbit"})
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
public class RabbitmqTest {
    @Autowired
    private ConfigurableApplicationContext cac;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试结合Spring Boot使用
     * 
     * <p>SpringBoot配置类为：RabbitProperties
     */
    @Test
    void withSpringBootTest() {
        System.out.println("rabbitTemplate=" + rabbitTemplate);
        // rabbitTemplate.receiveAndConvert() 似乎会因没有配置默认接收的队列而报错，而且确实没有配置项可以对此进行配置
//        Object result = rabbitTemplate.receiveAndConvert();
//        System.out.println(result);
        // use default routing key
        /*
         * 下一行，若没配置spring.rabbitmq.template.routing-key会抛异常：
         * org.springframework.amqp.AmqpIllegalStateException: No 'queue' specified. Check configuration of RabbitTemplate.
         */
        String queue = "queue";
        // 发送消息
        long msg = System.currentTimeMillis();
        rabbitTemplate.convertAndSend(queue, Long.toString(msg));
        System.out.println("发送消息：" + msg);
        msg = System.currentTimeMillis();
        rabbitTemplate.convertAndSend(queue, Long.toString(msg));
        System.out.println("发送消息：" + msg);

        // 接收消息；为什么会是null呢？因为之前定义了监听器。。。
        String reciveMsg = null;
        do {
            reciveMsg = (String) rabbitTemplate.receiveAndConvert(queue);
            System.out.println("接收消息：" + reciveMsg);
        } while (reciveMsg != null);

        // 如果发送的消息没被接收，会发生什么呢？
        rabbitTemplate.convertAndSend("queue", "see you next time");
    }
}
