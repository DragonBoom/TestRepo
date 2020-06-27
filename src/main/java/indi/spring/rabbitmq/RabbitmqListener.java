package indi.spring.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 测试通过SpringBoot的注解配置rabbit监听器
 * @author wzh
 * @since 2020.01.11
 */
@Component
public class RabbitmqListener {
    
    /**
     * 有该注解的方法将自动监听并处理指定的队列
     * 
     * @param msg
     */
    @RabbitListener(queues = "queue", containerFactory="")
    public void processMsg(String msg) {
        System.out.println("RabbitmqListener: receive msg:" + msg);
    }

}
