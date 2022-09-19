/**
 * 
 */
package indi.oracle.java.util;

import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

/**
 * @author wzh
 * @since 2020.10.04
 */
@ExtendWith(TestSeparateExtension.class)
class BlockingQueueTest {

    @Test
    void forEachTest() {
        // 1. 测试foreach是否会使元素被移除 - 不会
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        for (String str : queue) {
            System.out.println(str);
        }
        Assertions.assertEquals(3, queue.size());
    }
}
