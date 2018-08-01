package indi.oracle.java.exception;

import org.junit.jupiter.api.Test;

public class ExceptionTest {

    /**
     * Java 异常自上而下层层抛出
     */
    @Test
    void sequenceTest() {
        String a = null;
        a.chars();
    }
}
