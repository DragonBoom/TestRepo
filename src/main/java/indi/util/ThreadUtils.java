package indi.util;

import java.util.concurrent.TimeUnit;

import indi.exception.WrapperException;

public class ThreadUtils {
    
    /**
     * 给定时间内未能完成，则结束任务
     * 
     * @param function
     * @param millis
     * @return false - 未能按时完成，已终止任务
     */
    public static final boolean run(Runnable function, int millis, int sleepMillis) {
        Long deadlineMillis = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < deadlineMillis) {
            function.run();
            try {
                TimeUnit.MILLISECONDS.sleep(sleepMillis);
            } catch (InterruptedException e) {
                throw new WrapperException(e);
            }
        }
        return true;
    }
    
    public static final boolean hang(int sleepMillis, int millis) {
        Long deadlineMillis = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < deadlineMillis) {
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                throw new WrapperException(e);
            }
        }
        return true;
    }
}
