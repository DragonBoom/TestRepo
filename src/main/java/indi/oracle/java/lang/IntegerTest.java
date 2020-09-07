package indi.oracle.java.lang;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class IntegerTest {
    
    @Test
    @Disabled
    void equalsTest() {
        int iBase1 = 129;
        System.out.println(Integer.valueOf(iBase1) == Integer.valueOf(iBase1));// print: false
        
        // 使用valueOf方法创建-128~127的整型
        int iBase2 = 127;
        System.out.println(Integer.valueOf(iBase2) == Integer.valueOf(iBase2));// print: true
        
        // 使用new方法创建整型
        System.out.println(new Integer(iBase2) == new Integer(iBase2));// print: false
        
        // 这是因为字面量为-128~127的通过valueOf方法创建的Integer都取自缓存，缓存可见Integer的内部类IntegerCache
    }

    @Test
    @Disabled
    void parseIntTest() {
        int i1 = Integer.parseInt("0123");
        System.out.println(i1);// case 123 即0会被忽略
    }
    
    @Test
    @Disabled
    void paramTest() {
        int i = 1;
        setZero(1);
        System.out.println(i);// case 1 
        Integer i2 = 1; 
        setZero2(i2);
        System.out.println(i2);// case 1
    }
    
    @Test
    @Disabled
    void binaryTest() {
        Integer i = 8;
        System.out.println(i >> 1);// 8 / 2^1
        System.out.println(i >> 32);// case 8 ?
        System.out.println(i >> 31);// case 0 ?
        
        long l = 8;
        System.out.println(l >> 32);// case 0
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
