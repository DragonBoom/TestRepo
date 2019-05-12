package indi.oracle.java.lang;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.PrintUtils;
import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class CharacterTest {

    @Test
    @Disabled
    void equalsTest() {
        Character c = '6';
        if (c.equals('6')) {
            System.out.println("equals");
        }
    }
    
    // java 默认使用unicode编码，支持几乎所有文字，char类型默认值为空格(0)
    @Test
    @Disabled
    public void newArrayTest() {
        char[] chars = new char[5];
        for (char c : chars) {
            System.out.print("->  ");
            System.out.println((int) c);
        }
        char c = '中';
        System.out.println((int) c);
    }
    
    // 字节(byte)是计算机中存储数据的单位，而字符(char)是文字或符号
    // 同时byte 与 char 也是java中的一种基本数据类型
    @Test
    @Disabled
    public void staticFieldsTest() {
        System.out.println("the number of byte to represent a char: " + Character.BYTES);
    }
    
    /**
     *  字符长度测试
     */
    @Test
    @Disabled
    void lengthTest() {
        PrintUtils.with(str -> ((String)str).toCharArray().length)
                .print("1")
                .print("2")
                .print("啊")
                .print("a")// [a] -> 1
                .print("繁体")// [繁体] -> 2
                ;

    }
    
    @Test
    @Disabled
    void string2CharTest() {
        System.out.println(Character.toString('a'));// => String.toString('a');
    }
    
    @Test
    @Disabled
    void unicodeTest() {
        Character c = new Character('a');
        System.out.println(c >= 0x4e00 && c <= 0x9fa5);// false
        Character c1 = new Character('啊');
        System.out.println(c >= 0x4e00 && c1 <= 0x9fa5);// true
    }
    
    @Test
    void englishTest() {
        System.out.println('a' + 0);// 97
        System.out.println('A' + 0);// 65
        System.out.println('z' + 0);// 122
        System.out.println('Z' + 0);// 90
        // 综上，大小写字母的编码并不是连续的
    }
}
