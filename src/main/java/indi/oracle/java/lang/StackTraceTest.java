package indi.oracle.java.lang;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class StackTraceTest {

    /**
     * 利用堆栈记录，获取当前类的上一级调用者
     * 
     * @throws ClassNotFoundException
     */
    @Test
    void go() throws ClassNotFoundException {
        System.out.println(getCallerClass());
    }

    public static Class<?> getCallerClass() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println(Arrays.toString(stackTrace));
        try {
            for (int i = 0; i < stackTrace.length; i++) {
                if (StackTraceTest.class.getName().equals(stackTrace[i].getClassName())) {
                    return Class.forName(stackTrace[i + 1].getClassName());
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Class Not Found By Stack Trace : " + Arrays.asList(stackTrace));
    }
}
