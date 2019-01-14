package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;

public class StringTest {

    // @Test
    void splitTest() {
        String s = new String("www|fff|www");
        String[] afterSplit = s.split("\\|");
        for (String str : afterSplit) {
            System.out.println(str);
        }
    }
    
    // @Test
    void formatTest() {
        String s = new String("wwwww\nwwww");
        System.out.println(s);
        System.out.println(s.replace("\n", ""));
    }
    
    /**
     * 换行测试
     */
//    @Test
    void newLineTest() {
        String x = "a\nf";
        
        System.out.println(x.indexOf("\n"));
        System.out.println(x);
    }
    
    /**
     * 测试字符串与null相加
     */
//    @Test
    void addNullTest() {
        System.out.println("ff"+ null);
    }
    
    /**
     * 测试空字符串
     */
//    @Test
    void emptyStrTest() {
        String empty = "";
        String[] strs = empty.split(",");
        System.out.println(strs);
        for (String string : strs) {
            System.out.println("is it exist? " + string);
        }
    }
    
}
