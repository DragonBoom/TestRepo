package indi.oracle.java.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class StringTest {

    //    @Test
    void char2StringTest() {

        String c = "(213_)]";
        Pattern p = Pattern.compile("[\\w\\[\\]\\-\\(\\)]");
        Matcher m = p.matcher(c);
        int count = 0;
        while (m.find()) {
            count++;
        }
        System.out.println(count);
    }

    // @Test
    void spilitTest() {
        String str = "开始";
        for (String ele : str.split("\\W")) {
            System.out.println(ele);
        }
    }

    void constructorTest() {
        String[] strs = new String[] { "123", "123" };
    }

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
