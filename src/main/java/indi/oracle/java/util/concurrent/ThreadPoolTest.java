/**
 * 
 */
package indi.oracle.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import indi.util.TestUtils;

/**
 * @author wzh
 * @since 2020.07.06
 */
class ThreadPoolTest {

    @Test
    void go() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> {
            System.out.println("1");
        });
        executorService.submit(() -> {
            System.out.println("2");
        });
        executorService.submit(() -> {
            System.out.println("3");
        });
        
        executorService.execute(() -> {
            System.out.println("4");
        });
        TestUtils.holdUntil(10000);
        
        // 结束线程池
        executorService.shutdown();
    }
}
