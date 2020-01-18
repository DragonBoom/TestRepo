package indi.spring.redis;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.test.TestSeparateExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
@ActiveProfiles("redis")// 指定 properties-redis.properties 配置文件
public class SpringRedisTest {
    @Autowired
    private StringRedisTemplate strRedisTemplate;
    
    @Autowired(required = false)
    private RedisTemplate<String, Object> strObjRedisTemplate;

    @Test
    void firstTest() {
        System.out.println(strRedisTemplate);
        ValueOperations<String, String> opsForValue = strRedisTemplate.opsForValue();
        opsForValue.set("test", "testV");
        Object v = opsForValue.get("test");
        System.out.println(v);
    }
    
    @Test
    void queueTest() {
        System.out.println(strObjRedisTemplate);
        ListOperations<String, Object> ops = strObjRedisTemplate.opsForList();
        System.out.println(ops.rightPush("1", 1));
        System.out.println(ops.rightPush("1", 2));
        System.out.println(ops.rightPush("1", 3));
        System.out.println(ops.rightPush("1", 4));
        System.out.println(ops.leftPop("1"));
        System.out.println(ops.leftPop("1"));
        System.out.println(ops.leftPop("1"));
        System.out.println(ops.leftPop("1"));
        System.out.println();
        // 阻塞一段时间地出队，以此可实现简单的消息队列
        Object v = ops.leftPop("1", 10, TimeUnit.SECONDS);
        System.out.println(v);
    }
    
    private String LOCK_KEY_PREFIX = "lock";
    
    @Test
    void lockTest() {
        String lockKey = LOCK_KEY_PREFIX + 123;
        ValueOperations<String, Object> ops = strObjRedisTemplate.opsForValue();
        // 借助setnx指令，获得原子性
        boolean hasLock = ops.setIfAbsent(lockKey, 1);// like setnx
        // 获取不到锁则需要在一定限度内重试
        if (hasLock) {
            // 获取到锁，需要避免死锁，如设置30s后过期
            // 该操作原理是啥，安全吗，万无一失吗？
            strObjRedisTemplate.expire(lockKey, 30, TimeUnit.SECONDS);
            // 要是获取到锁后，设置过期失败？
        }
        System.out.println(hasLock);
    }
}
