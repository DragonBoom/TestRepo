package indi.spring.redis;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
@ActiveProfiles("redis")// 指定 properties-redis.properties 配置文件
public class SpringRedisTest {
    @Autowired
    private StringRedisTemplate strRedisTemplate;
    
    @Autowired(required = false)
    private RedisTemplate<String, Object> strObjRedisTemplate;
    
    @Autowired(required = false)
    private RedisTemplate<?, ?> redisTemplate;
    
    @Autowired(required = false)
    @Lazy
    private SimpleRedisLock simpleRedisLock;

    @Test
    @Disabled
    void firstTest() {
        System.out.println(strRedisTemplate);
        ValueOperations<String, String> opsForValue = strRedisTemplate.opsForValue();
        opsForValue.set("test", "testV");
        Object v = opsForValue.get("test");
        System.out.println(v);
    }
    
    @Test
    @Disabled
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
    
    /**
     * 测试用redis实现分布式同步锁
     */
    @Test
//    @Disabled
    void lockTest() throws InterruptedException {
        // 若使用默认序列化器，Redis的键为：\xac\xed\x00\x05t\x00\alock123
        String lockKey = LOCK_KEY_PREFIX + 123;
        System.out.println("lock key: " + lockKey);
        Object lockSign = simpleRedisLock.lock(lockKey);
        // do something
        System.out.println("lock done !!");
        Thread.sleep(20000);
        // 此时可手动访问Redis修改值以测试事务回滚
        simpleRedisLock.unlock(lockKey, lockSign);
        System.out.println("unlock done !!");
    }
    
}
