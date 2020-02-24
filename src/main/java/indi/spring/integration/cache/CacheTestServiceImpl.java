/**
 * 
 */
package indi.spring.integration.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wzh
 * @since 2020.02.17
 */
@Service
public class CacheTestServiceImpl implements CacheTestService {
    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    @Override
    @CachePut(cacheNames = "cacheTest1", key = "#name")// 指定参数p1作为key的参数，具体方法见注释
    public String setConfName(String name, String v1) {// 注意该方法的返回值才会被当作Cache的value！！！
        map.put(name, v1);
        return v1;
    }

    @Override
    @Cacheable(cacheNames = "cacheTest1")
    public String getConfName(String p1) {
        return map.get(p1);
    }

    @Override
    @CachePut(cacheNames = "cacheTest1")
    public void cleanConfName(String p1) {
        map.remove(p1);
    }

    @Override
    public void setConfNameNoCache(String p1, String v1) {
        // 不经过代理地调用，不会使用到Spring的任何代理相关机制 ？Y
        this.setConfName(p1, v1);
    }
    
}
