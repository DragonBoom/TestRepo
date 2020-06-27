package indi.oracle.java.util;

import java.util.LinkedList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class StreamTest {

    /**
     * 测试执行顺序<br> 
     * 结果为 1 1 2 2 3 3 4 5，即对每个元素，一次性执行完filter与 map后再处理下一个元素
     */
    @Test
    void orderTest() {
        LinkedList<Object> l = new LinkedList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
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
        LinkedList<Object> l = new LinkedList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
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
}
