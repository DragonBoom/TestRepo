package indi.oracle.java.util.concurrent;

import indi.util.extension.TestSeparateExtension;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: DragonBoom
 * @since: 2022/10/18 21:12
 */
@ExtendWith(TestSeparateExtension.class)
class FutureTaskTest {

    /*
     * 初试
     * 
     * @author DragonBoom
     * @since 2022-10-18
     */
    @Test
    @SneakyThrows
    void go() {
        Thread.sleep(1000);
        System.out.println("hello1");
        Callable<String> fun = () -> {
//            System.out.println("i`ll sleep");
//            Thread.sleep(1000);
            return "hello";
        };
        FutureTask<String> ft = new FutureTask<>(fun);
        ft.run();// 执行线程
        String s = ft.get(1, TimeUnit.SECONDS);
        Assertions.assertEquals("hello", s);
    }
}
