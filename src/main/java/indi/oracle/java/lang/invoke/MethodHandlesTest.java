package indi.oracle.java.lang.invoke;

import java.lang.invoke.MethodHandles;

import org.junit.jupiter.api.Test;

class MethodHandlesTest {
   
    @Test
    void go() {
        System.out.println(MethodHandles.lookup().lookupClass());
    }
}
