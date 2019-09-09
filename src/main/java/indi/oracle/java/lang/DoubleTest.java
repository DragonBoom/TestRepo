package indi.oracle.java.lang;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class DoubleTest {
    
    @Test
    void formatTest() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String format = decimalFormat.format(1.789d);// print: 1.8 有四舍五入!
        System.out.println(format);
        format = decimalFormat.format(1.719d);
        System.out.println(format);
    }

}
