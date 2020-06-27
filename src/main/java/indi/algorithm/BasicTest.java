package indi.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class BasicTest {

    /**
     * 测试取余相关
     */
    @Test
    @Disabled
    void remainderTest() {
        System.out.println(1%10);// case: 1
        System.out.println(10%10);// case: 0
        System.out.println(-12%10);// case: -2
        
    }
    
    /**
     * 测试无符号右移
     */
    @Test
    void unsignedRightShift() {
        int result = 8 >>> 1;
        System.out.println(result);
        result = -8 >>> 2;// 8 : 10...1000 => -8 : 01111...1001 => -8 >>>2 : 0001111...110
        System.out.println(result);
        int expect = (int) (Math.pow(2, 30) - 2);// -2 = 00..10 => 111...110 ; 0010...0 + 111...110 = 0001111...110 
        System.out.println(expect);
        Assertions.assertEquals(expect, result);
    }
}
