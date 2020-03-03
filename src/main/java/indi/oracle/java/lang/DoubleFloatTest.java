package indi.oracle.java.lang;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
    void multiplyTest() {
        // 直接对浮点型相乘，会出现问题
        double d = 0.14d;
        System.out.println(d * 100);// println: 14.000000000000002
        float f = 0.14f;
        System.out.println(f * 100);// println: 14.0
    }

}
