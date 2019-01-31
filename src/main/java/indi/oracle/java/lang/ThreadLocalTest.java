package indi.oracle.java.lang;

import java.util.LinkedList;
import java.util.Stack;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    void go() {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("hehe");
        System.out.println(threadLocal.get());
    }
}
