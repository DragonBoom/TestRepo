package indi.rabbitmq;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-rabbit.properties")// @PropertySource默认不支持yaml读取
public class RabbitConfig {

}
