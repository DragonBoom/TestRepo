package indi.spring.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisStringObjectTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // RedisTemplate将利用InitializingBean接口的afterPropertiesSet方法，在Spring容器注入结束后初始化
        /*
         * 默认将注入 JdkSerializationRedisSerializer 作为默认的序列化工具，能实现字节数组到对象的序列化
         * 但对象必须实现Serializable接口
         */
        redisTemplate.setConnectionFactory(factory);
        // 开启对事物的支持
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }
}
