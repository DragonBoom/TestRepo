/**
 * 
 */
package indi.spring.integration.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

/**
 * @author wzh
 * @since 2020.02.17
 */
@SpringBootTest
@ExtendWith(value = {SpringExtension.class, TestSeparateExtension.class})
class SpringCacheTest {
    
    /**
     * 测试没有其他任何配置，缓存注解能否生效 N
     * 
     * @author DragonBoom
     * @since 2020.02.17
     */
    @Test
    @Disabled
    void defaultConfTest() {
        cacheTestService.setConfName("test", "testR");
        // 若注解没配置cache name，将抛异常 At least one cache should be provided per cache operation.
        // 注解加上cache name后，将尝试使用redis的cache manager(很可能是因为检测到了有redis的依赖)...
        String r1 = cacheTestService.getConfName("test");
        System.out.println(cacheTestService.getConfName("test"));
        // 修改实际值，但缓存保持不变
        cacheTestService.setConfNameNoCache("test", "testR2");
        String r2 = cacheTestService.getConfName("test");
        Assertions.assertEquals(r1, r2);
    }
    
    /**
     * 第一步测试：于配置类配置了基于ConcurrentHashMap的CacheManager的bean，然后执行测试方法，测试成功
     * 
     * @author DragonBoom
     * @since 2020.02.17
     */
    @Test
    void firstTest() {
        cacheTestService.setConfName("test", "result1");// 已指定参数p1作为key的参数
        // 若注解没配置cache name，将抛异常 At least one cache should be provided per cache operation.
        // 注解加上cache name后，将尝试使用redis的cache manager(很可能是因为检测到了有redis的依赖)...
        String r1 = cacheTestService.getConfName("test");
        System.out.println(cacheTestService.getConfName("test"));
        // 修改实际值，但缓存保持不变
        cacheTestService.setConfNameNoCache("test", "result2");
        System.out.println("----");
        String r2 = cacheTestService.getConfName("test");
        Assertions.assertEquals(r1, r2);// success!
    }
    
    @Autowired
    private CacheTestService cacheTestService;

}
