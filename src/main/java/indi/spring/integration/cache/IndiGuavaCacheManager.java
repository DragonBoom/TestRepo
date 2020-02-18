package indi.spring.integration.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * 简单实现基于GuavaCache的CacheManager，支持Spring管理的事务，事务回滚时不会修改缓存。
 * <p>
 * 实际上，如果不是为了事务，直接实现CacheManager接口更加简单，AbstractTransactionSupportingCacheManager的父类AbstractCacheManager的提供
 * 的默认实现并没有太大意义。
 * <p>
 * 该类线程安全（guava cache提供）
 * 
 * @author wzh
 * @since 2020.02.17
 */
public class IndiGuavaCacheManager extends AbstractTransactionSupportingCacheManager {
    private com.google.common.cache.Cache<Object, Object> googleCache;
    @Setter
    private boolean isPrint = false; 
    
    
    public IndiGuavaCacheManager(com.google.common.cache.Cache<Object, Object> googleCache) {
         this.googleCache = googleCache;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        // 不需要任何初始化的缓存
        return Collections.emptyList();
    }

    // 不想使用AbstractCacheManager的逻辑，实现该接口即可；但有隐患，父类仍会记录该方法返回的Cache，会浪费一点内存。。。
    @Override
    protected Cache getMissingCache(String name) {
        return new GuavaCache(googleCache, isPrint);
    }
    
    /**
     * guava version
     * 
     * @author wzh
     * @since 2020.02.17
     */
    @AllArgsConstructor
    class GuavaCache implements Cache {
        private com.google.common.cache.Cache<Object, Object> googleCache;
        private boolean isPrint = false; 

        @Override
        public String getName() {
            return "GuavaCache";
        }

        // 返回Cache的实际提供者
        @Override
        public Object getNativeCache() {
            return googleCache;
        }

        @Override
        public ValueWrapper get(Object key) {
            if (isPrint) System.out.println("get: " + key);
            @Nullable
            Object v = googleCache.getIfPresent(key);
            return v == null ? null : () -> v;// 与父类的注释一致，找不到值直接返回null
        }

        @Override
        public <T> T get(Object key, Class<T> type) {
            if (isPrint) System.out.println("get with type: " + key + " " + type);
            return (T) googleCache.getIfPresent(key);
        }

        @Override
        public <T> T get(Object key, Callable<T> valueLoader) {
            // if cached, return; otherwise create, cache and return
            if (isPrint) System.out.println("get with valueLoader: " + key);
            try {
                T obj = (T) googleCache.get(key, valueLoader);
                return obj;
            } catch (ExecutionException e) {
                throw new ValueRetrievalException(key, valueLoader, e);// 按接口注释抛出该异常...
            }
        }

        @Override
        public void put(Object key, Object value) {// key 的实现类为Spring自行封装的SimpleKey，因此key不能指定类型
            if (isPrint) System.out.println("put: " + key + " " + value);
            googleCache.put(key, value);
        }

        @Override
        public ValueWrapper putIfAbsent(Object key, Object value) {
            // 注意线程安全...
            // 这里由于本方法与guava的实现相悖，但又不想抛开guava cache另外实现线程安全，因此当作值一定存在处理
            if (isPrint) System.out.println("put if absent: " + key + " " + value);
            try {
                Object object = googleCache.get(key, () -> value);
                return () -> object;
            } catch (ExecutionException e) {
                throw new RuntimeException(e);// 接口注释没有说明如何抛出异常，这里直接抛RuntimeException
            }
        }

        @Override
        public void evict(Object key) {
            if (isPrint) System.out.println("evict: " + key);
            googleCache.invalidate(key);
        }

        @Override
        public void clear() {
            if (isPrint) System.out.println("cleanUp");
            googleCache.cleanUp();// sure ? FIXME:
        }
        
    }

}
