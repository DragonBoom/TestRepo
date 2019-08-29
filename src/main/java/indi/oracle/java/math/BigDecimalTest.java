package indi.oracle.java.math;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class BigDecimalTest {

    @Test
    void compareTest() {
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(2);
        
        System.out.println(bigDecimal1.compareTo(bigDecimal2));// case -1
    }
    
    @Test
    void toStringTest() {
        System.out.println(new BigDecimal(123));// println: 123
    }
}
