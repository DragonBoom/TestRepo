package indi.oracle.java.thread;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import indi.oracle.java.thread.ThreadSleepInterruptTest.CycleThread;

class ThreadSleepInterruptTest {

    /**
     * 测试证明，对sleep中的线程执行interrupt方法会使目标线程抛出异常，从而结束sleep
     * 
     * @throws InterruptedException
     */
    @Test
    void go() throws InterruptedException {
        CycleThread cycleThread = new CycleThread();
        cycleThread.start();
        Thread.sleep(2000);
        cycleThread.interrupt();
        System.out.println("interrupt over");
        new HoldOnHelperThread().run();
    }

    class CycleThread extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("I`m sleep");
                try {
                    TimeUnit.SECONDS.sleep(100L);
                    System.out.println("I`m wake now!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("I`m wake now !~~~");
                }
            }
        }
    }

}
