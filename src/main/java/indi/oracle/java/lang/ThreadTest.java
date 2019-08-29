package indi.oracle.java.lang;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.ThreadUtils;
import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ThreadTest {

    Thread newThread(String toPrintln) {
        Thread t = new Thread(() -> {
            while (true) {
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    throw new WrapperException(e);
//                }
                System.out.println(toPrintln);
            }
        });
        return t;
    }

    /**
     * 测试interrupt方法
     */
    @Test
    void interruptTest() throws InterruptedException {
        Thread t = newThread("hello");
        t.start();
        TimeUnit.SECONDS.sleep(6);
        t.interrupt();
        System.out.println("interrupt");// 线程t在调用了interrupt方法后仍会打印数据，即仍会执行
        System.exit(-1);
        ThreadUtils.run(() -> {System.out.println(t);}, 500, 20000);
    }
}
