/**
 * 
 */
package indi.spring.integration.cache;

import java.time.Duration;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

/**
 * @author wzh
 * @since 2020.02.17
 */
@Configuration
public class SpringCacheTestConf {
    public static final String CONCURRENT_MAP_CACHE_NAME = "concurrentMap";

    /**
     * Spring 提供的默认实现：concurrentMap
     * 
     * @author DragonBoom
     * @since 2020.02.17
     * @return
     */
//    @Bean
    public CacheManager concurrentMapCacheManager() {
        return new ConcurrentMapCacheManager();
    }
    
    @Bean
    public CacheManager indiGuavaCacheManager() {
        com.google.common.cache.Cache<Object, Object> googleCache = CacheBuilder.newBuilder()
                .expireAfterAccess(Duration.ofHours(2))// 每个缓存2小时后过期
                .build();
        IndiGuavaCacheManager cacheManager = new IndiGuavaCacheManager(googleCache);
        cacheManager.setPrint(true);
        return cacheManager;
    }
    
//   不用注册该bean。。。 
//    @Bean
//    public Cache concurrentMapCache() {
//        return new ConcurrentMapCache(CONCURRENT_MAP_CACHE_NAME);
//    }
}
