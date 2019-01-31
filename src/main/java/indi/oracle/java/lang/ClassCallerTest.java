package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;

class ClassCallerTest {

    @Test
    void go() {
        System.out.println(StackTrace.getCallerClass());;
    }
}
