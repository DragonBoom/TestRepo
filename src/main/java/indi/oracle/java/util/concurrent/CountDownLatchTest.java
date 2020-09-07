/**
 * 
 */
package indi.oracle.java.util.concurrent;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

/**
 * @author wzh
 * @since 2020.07.05
 */
class CountDownLatchTest {

    @Test
    void simpleTest() throws InterruptedException {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        countDownLatch.await();
        countDownLatch.countDown();
    }
}
