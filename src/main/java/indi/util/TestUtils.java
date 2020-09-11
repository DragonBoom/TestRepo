/**
 * 
 */
package indi.util;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

import javax.annotation.Nullable;

import indi.exception.WrapperException;

/**
 * @author wzh
 * @since 2020.09.09
 */
public class TestUtils {
    
    /**
     * 挂起当前线程直至完成任务或超时
     * 
     * @param function 返回任务完成与否的方法，返回true表示任务完成，将不断轮询该方法
     * @param timeoutMillis 超时时间，毫秒
     * @param sleepMillis 每次轮询间隔
     * @return 返回true表示因任务完成而结束，false表示因超时而结束
     */
    public static final boolean holdUntil(@Nullable BooleanSupplier function, int timeoutMillis, int sleepMillis) {
        Long deadlineMillis = System.currentTimeMillis() + timeoutMillis;
        while (System.currentTimeMillis() < deadlineMillis && !function.getAsBoolean()) {
            try {
                TimeUnit.MILLISECONDS.sleep(sleepMillis);
            } catch (InterruptedException e) {
                throw new WrapperException(e);
            }
        }
        return true;
    }
    
    /**
     * 挂起当前线程指定时间。具体实现为简单的Thread.sleep
     * 
     * @param timeoutMillis 挂起时间，毫秒
     * @return 返回true表示因任务完成而结束，false表示因超时而结束
     * @return
     */
    public static final boolean holdUntil(int timeoutMillis) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeoutMillis);
        } catch (InterruptedException e) {
            throw new WrapperException(e);
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
