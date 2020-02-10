package indi.oracle.java.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
    
    @Test
    void devideTest() {
        BigDecimal divide = null;
        // default
        // case: java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        // 无限小数抛异常
//        BigDecimal divide = BigDecimal.valueOf(40).divide(BigDecimal.valueOf(7));
        divide = BigDecimal.valueOf(40).divide(BigDecimal.valueOf(80));// print: 0.5
        System.out.println(divide);
        // 取小数点后3位，满5加1
        divide = BigDecimal.valueOf(20000).divide(BigDecimal.valueOf(6), 3, RoundingMode.HALF_UP);
        System.out.println(divide);// print: 5.71
    }
}
