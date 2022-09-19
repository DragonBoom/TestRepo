package indi.oracle.java.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;
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
        System.out.println(new BigDecimal(0.1));// println: 0.1000000000000000055511151231257827021181583404541015625
    }
    
    @Test
    void devideTest() {
        Assertions.assertEquals("0.500", BigDecimal.valueOf(1).divide(BigDecimal.valueOf(2), 3, RoundingMode.HALF_UP).toString());
        
        BigDecimal divide = null;
        // default
        // case: java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        // 结果为 无限小数(Non-terminating decimal) 时将抛异常，除非设置了舍入条件
        // divide = BigDecimal.valueOf(40).divide(BigDecimal.valueOf(7));
        divide = BigDecimal.valueOf(40).divide(BigDecimal.valueOf(80));// print: 0.5
        System.out.println(divide);
        // 取小数点后3位，满5加1
        divide = BigDecimal.valueOf(20000).divide(BigDecimal.valueOf(6), 3, RoundingMode.HALF_UP);
        System.out.println(divide);// print: 3333.333
        System.out.println(divide.doubleValue());// print: 3333.333
    }
}
