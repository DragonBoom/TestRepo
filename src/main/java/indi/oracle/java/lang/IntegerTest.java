package indi.oracle.java.lang;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class IntegerTest {

    @Test
    @Disabled
    void parseIntTest() {
        int i1 = Integer.parseInt("0123");
        System.out.println(i1);// case 123 即0会被忽略
    }
    
    @Test
    void paramTest() {
        int i = 1;
        setZero(1);
        System.out.println(i);// case 1 
        Integer i2 = 1; 
        setZero2(i2);
        System.out.println(i2);// case 1
    }
    
    private int setZero(int x) {// 这里的x与上面的i都指向相同的地址，但对于整型，x = 0没有修改了地址的值，而是将x指向了0所在的地址
        x = 0;
        return 0;
    }
    
    private int setZero2(Integer x) {// 同上
        x = 0;
        return 0;
    }
}
