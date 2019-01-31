package indi.google.guava.collection;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.google.common.cache.CacheBuilder;

public class GuavaCacheTest {

    @Test
    void go() {
        Future<Boolean> future;
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();
        // Guava Cache 对标的是 Map 而不是List!!
    }
    
    private class MyTask {
        private Consumer<Map<String, Object>> function;
        private Predicate<Map<String, Object>> predicate;
        private Map<String, Object> params;
        private Date deadDate;
        
        void run() {
            function.accept(params);
        }
        
        boolean check() {
            return predicate.test(params);
        }

    }
    
    
}
