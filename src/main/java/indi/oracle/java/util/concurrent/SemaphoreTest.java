/**
 * 
 */
package indi.oracle.java.util.concurrent;

import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

/**
 * Semaphore即信号量，用于控制同时执行逻辑的线程数量；可实现一次占用多个配额的效果
 * 
 * @author wzh
 * @since 2020.07.05
 */
class SemaphoreTest {
    
    @Test
    void simpleTest() throws InterruptedException {
        Semaphore semaphore = new Semaphore(10, false);
        semaphore.acquire();
        semaphore.acquire(10);
        semaphore.release();
        semaphore.release(10);
    }

}
