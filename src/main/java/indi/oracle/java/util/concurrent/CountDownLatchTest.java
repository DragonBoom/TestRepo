/**
 * 
 */
package indi.oracle.java.util.concurrent;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

/**
 * CountDownLatch用于实现每n个线程执行countDown()后释放锁的效果
 * 
 * @author wzh
 * @since 2020.07.05
 */
class CountDownLatchTest {

    /**
     * 
     * 
     * @throws InterruptedException
     * @author DragonBoom
     * @since 2020.09.14
     */
    @Test
    void simpleTest() throws InterruptedException {
        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        latch.countDown();
        System.out.println(latch);// prin: java.util.concurrent.CountDownLatch@4c40b76e[Count = 9]
        System.out.println(1);
        latch.await();// block here !
        // 仅当latch执行了9次countDown后才会执行到这里
        System.out.println(2);
        latch.countDown();
    }
}
