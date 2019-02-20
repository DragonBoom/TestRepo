package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class IntegerTest {

    @Test
    void parseIntTest() {
        int i1 = Integer.parseInt("0123");
        System.out.println(i1);// case 123 即0会被忽略
    }
}
