package indi.spring.redis;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * 简单的Redis锁组件，适用于并发量中型的场景，如果并发量低于毫秒级，可多机使用（受制于签名的生成方式，可专门为多机场景进行优化）
 * 
 * <p>相比于普通的同步锁，redis锁的优势是可多机使用，而且使用简单、管理方便，扩展空间大
 * 
 * <p>本方法不通过new创建锁的实例，而是通过返回签名的方式来定位锁，因为锁实际并非java对象，没必要硬将其转化为对象使用，同时这样也与Spring的机制更兼容
 * 
 * @author wzh
 * @since 2020.02.05
 */
@Component
public class SimpleRedisLock {
    private Long lockFailSleepMillis = 2000L;
    /** 最大重试次数 */
    private Integer maxRetryTimes = 3;
    /** 锁的最长有效时间 */
    private Integer expireSecends = 30;

    /** 锁的使用记录表，记录 键 + 最近一次持有的线程对象 */
    // 不适用于分布式架构，只用于单机场景的优化
    @Deprecated// 无法解决判断+行为可能不同步的问题
    private ConcurrentHashMap<String, Thread> lockTable = new ConcurrentHashMap<String, Thread>();
    
    public String generateLockSign() {
        return Long.toString(System.nanoTime());
    }
    
    /**
     * 锁
     * 
     * @param lockKey
     * @return 锁的签名，用于判断锁的归属
     */
    Object lock(String lockKey) {
        ValueOperations<String, Object> ops = strObjRedisTemplate.opsForValue();
        // 借助setnx指令，以类似cas的模式（原子地比较并修改），原子性地修改值，以此“锁”
        // 这种锁实现起来简单，缺点是状态能放在键的值
        boolean hasLock = false;
        int retryTimes = 0;// 重试次数，for log
        long beginTimes = System.currentTimeMillis(), // 开始时间,
                maxTimes = beginTimes + maxRetryTimes * expireSecends;// 最大截至时间
        // 生成锁的签名，并尝试通过setnx模式取得锁
        // 会不会存在一直获取不到锁的情况？（比如上锁后，突然关闭服务器）通过expire指令实现，使得基本上不会出现死锁的情况
        Object lockSign = null;
        System.out.println(lockKey);
        System.out.println(ops.get(lockKey));
        while (!(hasLock = ops.setIfAbsent(lockKey, lockSign = generateLockSign()))// = setnx
                && System.currentTimeMillis() < maxTimes) {
            try {
                // 最简单的轮询方式：让线程主动睡眠后重试
                Thread.sleep(lockFailSleepMillis);
                retryTimes++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 记录锁被当前线程使用
        System.out.println(ops.get(lockKey));
        
        lockTable.put(lockKey, Thread.currentThread());
        
        // 获取不到锁的，则需要在一定限度内重试（当持续一段时间的量非常大时，可能需要考虑长时间抢不到锁的情况）
        // 这种时候，与其依靠不断线程睡眠然后重试，可以考虑通过多线程队列实现公平的等待队列，但如何处理线程（等待过程中线程处于何种状态）、超时处理是个问题。
        if (hasLock) {
            // 获取到锁，为了避免线程或应用挂掉造成死锁，需要设置过期时间，如设置30s后过期
            // 该操作原理是啥，安全吗，万无一失吗？会不会这个操作本身就失败了？该操作应该是通过调用redis的指令实现
            strObjRedisTemplate.expire(lockKey, expireSecends, TimeUnit.SECONDS);
        } else {
            throw new RuntimeException("系统繁忙，请稍后再试");// 仅模拟，实际情况应该是返回对象
        }
        // 返回锁的签名
        return lockSign;
    }
    
    /**
     * 释放锁
     * 
     * @param lockKey
     * @param lockSign 锁的签名，加锁时获得，用于判断锁的归属
     */
    void unlock(String lockKey, Object lockSign) {
        // 不能直接删除键，此时key可能已过期并被其他线程持有
        Thread lastThread = lockTable.get(lockKey);
        // 只有当前线程是最后一次持有锁的线程时，说明锁仍被当前线程持有，有资格移除锁
        // 否则锁已被其他线程持有，可能仍被持有或已被释放
        // 并且，在判断与删除锁的这个过程中，存在线程不安全，即判断完毕但修改还未开始时，判断的结果已发生了变化，即仍存在线程安全问题
        // 判断+删除锁的这个过程应该尽量避免java实现，否则难以扩展到多个服务器使用，并且会进一步影响性能
        
        // new version:
        // 像通过签名机制，利用值存放签名的方式实现了对所的归属的判断
        // 接下来的问题就是要做到一致性(前后一致)地删除键
        // 通过watch指令 + redis事物实现 （这种实现方式应该都是通过redis指令实现的）
        // 必须先watch再multi，否则会报错
        strObjRedisTemplate.watch(lockKey);// 监视键，若提交事务时键的值与一开始监视时的不同（暂不清楚这个判断是不是最后才进行），将不提交事物
        
        // 2020.06.27 补充：检查锁是否仍被当前线程持有
        Object lockV = strObjRedisTemplate.opsForValue().get(lockKey);
        if (!lockSign.equals(lockV)) {
            throw new RuntimeException("系统繁忙，请稍后再试");// 此时锁已被其他线程持有
        }
        
        
        // 开始事物（RedisTemplate需要启用事务，见：RedisConfig）
        strObjRedisTemplate.multi();
        Boolean isDeleted = strObjRedisTemplate.delete(lockKey);
        System.out.println("isDeleted: " + isDeleted);// 若RedisTemplate不启用事物， print: true；启用事物后，print: false
        // 提交事物
        List<Object> execResults = strObjRedisTemplate.exec();
        if (execResults == null || execResults.size() == 0) {
            throw new RuntimeException("系统繁忙，请稍后再试");// 模拟而已，此时出现的问题是没有删除到键，可能是键已超时
        }
        System.out.println(execResults);// print [1]
    }
    
    @Autowired
    @Lazy
    private RedisTemplate<String, Object> strObjRedisTemplate;// 在RedisConfig中自定义的bean，已启用事务
}
