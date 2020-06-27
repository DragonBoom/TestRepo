package indi.google.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class GuavaCacheTest {

    /*
     * 主要想弄懂Guava如何进行线程同步，如何控制线程同步效率
     */
    void go() {
        /*
         * Cache 基本创建流程
         */
        Cache<String, Object> cb = CacheBuilder.newBuilder()
                .concurrencyLevel(4)// ?
                .build();
    }
}
