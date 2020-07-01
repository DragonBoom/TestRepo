package indi.oracle.java.lang;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.StringUtils;
import indi.util.ThreadUtils;
import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ThreadTest {

    /**
     * 创建新线程（对象），该线程通过不断打印数据表明自己仍存活
     * 
     * @author DragonBoom
     * @since 2020.06.28
     * @param toPrintln 为空时不打印
     * @return
     */
    Thread newThread(long sleepSeconds, String toPrintln, String interruptedExceptionMsg) {
        Thread t = new Thread(() -> {
            while (true) {
                if (!StringUtils.isEmpty(toPrintln)) {
                    System.out.println(toPrintln + "-start");
                }
                try {
                    TimeUnit.SECONDS.sleep(sleepSeconds);
                } catch (InterruptedException e) {
                    throw new RuntimeException(interruptedExceptionMsg, e);
                }
                if (!StringUtils.isEmpty(toPrintln)) {
                    System.out.println(toPrintln + "-end");
                }
            }
        });
        return t;
    }

    /**
     * 测试对睡眠中的线程执行interrupt方法
     */
    @Test
//    @Disabled
    void interruptWhileSleepTest() throws InterruptedException {
        Thread t = newThread(10, "hello", "throws interrrupted exception");
        t.start();
        TimeUnit.SECONDS.sleep(6);
        System.out.println("start call interrupt");
        t.interrupt();// 子线程抛异常：java.lang.RuntimeException: throws interrrupted exception
        System.out.println("is interrupted ?" + t.isInterrupted());// print false
        
        ThreadUtils.holdUntil(() -> {
            return !t.isAlive();
        }, 5000, 500);
        // 当前线程不受影响，仍能正常执行
        System.out.println("completed!");
    }

    /**
     * 测试对runnable（可运行）状态的线程执行interrupt方法
     */
    @Test
    @Disabled
    void interruptWhileRunnableTest() throws InterruptedException {
        Thread t = newThread(0, null, "throws interrrupted exception");
        t.start();
        TimeUnit.SECONDS.sleep(6);
        System.out.println("start call interrupt");
        t.interrupt();// 不会立即结束线程，但线程仍会被回收？N 永远不会结束，但测试用例会自行结束
        // 可通过isInterrupted()方法获取是否执行过interrupted
        // 能否执行interrupted？FIXME:
        System.out.println("is t interrupted? " + t.isInterrupted());// print: true
        System.out.println("is alive ?" + t.isAlive());// true
        // 下列打印结果表明，执行interrupt方法后不会改变线程状态
        System.out.println(t.getState());// print: RUNNABLE
        System.out.println("is alive ?" + t.isAlive());// true
//        ThreadUtils.holdUntil(() -> false, 500, 500);
        System.out.println("completed!");
    }
}
