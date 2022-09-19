package indi.oracle.java.lang;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
    
    @Test
    @Disabled
    void string2CharTest() {
        System.out.println(Character.toString('a'));// => String.toString('a');
    }
    
    @Test
    @Disabled
    void chineseUnicodeTest() {
        Character c = new Character('a');
        System.out.println(c >= 0x4e00 && c <= 0x9fa5);// false
        Character c1 = new Character('啊');
        System.out.println(c >= 0x4e00 && c1 <= 0x9fa5);// true
    }
    
    @Test
    @Disabled
    void englishTest() {
        System.out.println('1' + 0);// 49
        System.out.println('2' + 0);// 50
        
        
        System.out.println('a' + 0);// 97
        System.out.println('A' + 0);// 65
        System.out.println('z' + 0);// 122
        System.out.println('Z' + 0);// 90
        // 综上，大小写字母的编码并不是连续的
    }
    
    @Test
    void compareTest() {
        char c1 = ' ';
        char c2 = '1';
        char c3 = '_';
        char c4 = '\'';
        char c5 = '.';
        // 从小到大排序，得到结果与windows文件名排序一致（但测试样本不全，不保证所有字符的排序都一致）
        System.out.println(c1 < c2);// print: true
        System.out.println(c1 < c3);// print: true
        System.out.println(c1 < c5);// print: true
        System.out.println(c2 < c3);// print: true
        System.out.println(c4 < c3);// print: true
    }
}
