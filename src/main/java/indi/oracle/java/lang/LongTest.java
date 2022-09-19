package indi.oracle.java.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class LongTest {
    
    @Test
    void parseExceptionTest() {
        // 越界
        try {
            Long.parseLong(" " + Long.MAX_VALUE + 1);
        } catch (Exception e) {
            Assertions.assertEquals(NumberFormatException.class, e.getClass());
        }
    }

}
