package indi.oracle.java.util;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class StreamTest {
    LinkedList<Object> l = new LinkedList<>();
    
    @BeforeEach
    void init() {
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
    }

    /**
     * 测试执行顺序<br> 
     * 结果为 1 1 2 2 3 3 4 5，即对每个元素，一次性执行完filter与 map后再处理下一个元素
     */
    @Test
    @Disabled
    void orderTest() {
        l.stream()
            .filter(obj -> {
                System.out.println(obj);
                return true;
            })
            .map(obj -> {
                System.out.println(obj);
                return obj;
            })
            .count();
    }

    @Test
    @Disabled
    void closeTest() {
        l.stream()
            .filter(obj -> {
                System.out.println(obj);
                return true;
            })
            .map(obj -> {
                System.out.println(obj);
                return obj;
            })
            .close();// will print nothing
    }
    
    /**
     * 测试Stream的limit方法能否限制其他操作的执行次数，且是否与limit方法的位置有关：
     * 
     * 可以，无关
     * 
     * @author DragonBoom
     * @since 2020.09.04
     */
    @Test
    @Disabled
    void limitTest() {
        long count = l.stream()
            .filter(obj -> {
                System.out.println(obj);
                return true;
            })
            .limit(1)
            .count();
        System.out.println(count);
    }
    
    /**
     * 测试累加方法reduce()的使用：
     * T reduce(T identity, BinaryOperator<T> accumulator);
     * 
     * 该方法的第一个参数identity实际上就是累加的起始值，第二参数是累加函数
     * 
     * @since 2021.12.14
     */
    @Test
    void reduceTest() {
        Integer r = l.stream()
                .map(o -> (String) o)
                .map(Integer::parseInt)
                .reduce(9, (total, next) -> total + next);
        Assertions.assertEquals(19, r);
        
        r = l.stream()
                .map(o -> (String) o)
                .map(Integer::parseInt)
                .reduce(0, (total, next) -> total + next);
        Assertions.assertEquals(10, r);
    }
}
