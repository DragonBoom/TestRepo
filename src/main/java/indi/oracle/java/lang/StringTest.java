package indi.oracle.java.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StringTest {

    @Test
    @Disabled
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

    /**
     * 测试使用对不满足分隔符的表达式的字符串使用split方法会有什么结果
     * <p>
     * 已知：不会报错
     */
     @Test
     @Disabled
    void noMatchSspilitTest() {
        String str = "开始";
        for (String ele : str.split("\\W")) {
            System.out.println(ele);
        }
    }

    @Test
    @Disabled
    void formatTest() {
        String s = new String("wwwww\nwwww");
        System.out.println(s);
        System.out.println(s.replace("\n", ""));
    }
    
    /**
     * 换行测试
     */
    @Test
    @Disabled
    void newLineTest() {
        String x = "a\nf";
        
        System.out.println(x.indexOf("\n"));
        System.out.println(x);
    }
    
    /**
     * 测试字符串与null相加
     */
    @Test
    @Disabled
    void addNullTest() {
        System.out.println("ff"+ null);
    }
    
    /**
     * 测试字符串hashCode
     */
    @Test
    void hashCodeTest() {
        "hello".hashCode();
    }
}
