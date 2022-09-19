package indi.oracle.java.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class DoubleFloatTest {
    
    /**
     * 测试将double转化为字符串
     * @author DragonBoom
     * @since 2020.02.24
     */
    @Test
    @Disabled
    void formatTest() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String format = decimalFormat.format(1.789d);
        System.out.println(format);// print: 1.8
        format = decimalFormat.format(1.719d);
        System.out.println(format);// print: 1.7
        format = decimalFormat.format(111.71911d);
        System.out.println(format);// print: 111.7
        
        // 也可以通过String.format实现该效果
    }
    
    /**
     * 测试double的除法
     * 
     * @author DragonBoom
     * @since 2020.02.25
     */
    @Test
    @Disabled
    void divideTest() {
        double dividend = 2000d;// 被除数
        double divisor = 6d;// 除数
        double quotient;// 商
        
        // 1. direct
        quotient = dividend / divisor;
        System.out.println(quotient);// print: 333.3333333333333
        
        // 2. BigDecimal
        BigDecimal bd = BigDecimal.valueOf(dividend).divide(BigDecimal.valueOf(divisor), 2, RoundingMode.HALF_UP);
        quotient = bd.doubleValue();
        System.out.println(quotient);// print: 333.33
        // 设置小数点后的位数，浮点数必须指定舍入形式(rounding mode)，否则会抛异常
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        quotient = bd.doubleValue();
        System.out.println(quotient);// print: 333.3
        
        
        System.out.println(quotient * 100);// print: 33330.0
    }
    
    // 乘法测试
    @Test
    @Disabled
    void multiplyTest() {
        // 直接对浮点型相乘，会出现问题
        double d = 0.14d;
        System.out.println(d * 100);// println: 14.000000000000002
        float f = 0.14f;
        System.out.println(f * 100);// println: 14.0
    }
    
    /**
     * 测试是否需要后缀
     * 
     * @since 2021.12.14
     */
    @Test
    @Disabled
    void go() {
        double dd = 8 / 3;
        Assertions.assertTrue(dd == 2);
        dd = 8D / 3;
        System.out.println(dd);// 2.6666...
        Assertions.assertTrue(dd != 2);
    }
    
    /**
     * 测试有效数字问题：当小于1时，有效数字不计0；超过有效位数的值将被四舍五入
     * 
     * @since 2022-09-10
     */
    @Test
    @Disabled
    void significantNumberTest() {
        System.out.println(0.123456789012345678);// 0.12345678901234568
        System.out.println((float)0.1234567890);// 0.12345679
        System.out.println((float)1.1234567890);// 0.12345679
    }
    
    /**
     * 测试浮点型的奇怪的转换：浮点型从低精度转化为高精度时，会用非0值填充多出来的位数
     * 
     * @since 2022-09-10
     */
    @Test
    void transforTest() {
        Assertions.assertTrue(0.1f != 0.1d);
        
        System.out.println(0.1f + 0.1d);// 0.20000000149011612
        Assertions.assertTrue(0.1f + 0.1d != 0.2d);
        
        System.out.println((float)0.1d);// 0.1
        System.out.println((double)0.1f);// 0.10000000149011612
        Assertions.assertTrue((float)0.1d == 0.1f);// 高精度转发为低精度时不会出意外
        Assertions.assertTrue((double)0.1f != 0.1d);
    }

}
