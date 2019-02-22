package indi.oracle.java.lang;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class CompareToTest {

    @Test
    void go() {
        System.out.println(new BigDecimal(0).compareTo(new BigDecimal(1)));// case -1
        System.out.println(new BigDecimal(0).compareTo(new BigDecimal(0)));// case 0
    }
}
