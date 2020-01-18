package indi.oracle.java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Formatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.apache.bcel.classfile.FieldOrMethod;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.util.StringUtils;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
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
     * 测试使用对不包含分隔符的表达式的字符串使用split方法会有什么结果
     * <p>
     * 不会报错
     */
     @Test
     @Disabled
    void noMatchSspilitTest() {
        String str = "开始";
        for (String ele : str.split("\\W")) {
            System.out.println(ele);
        }
    }

     /**
      * 测试特殊字符（换行符）的替换
      */
    @Test
    @Disabled
    void specialReplaceTest() {
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
        System.out.println("ff"+ null);// case: ffnull
    }
    
    /**
     * 测试字符串hashCode
     */
    @Test
    @Disabled
    void hashCodeTest() {
        "hello".hashCode();
    }
    
    /**
     * 测试静态方法String.format
     */
    @Test
    @Disabled
    void formatTest() {
        System.out.println(String.format("开始 1$", "了"));// 设置参数失败，case : 开始 1$
        System.out.println(String.format("开始 %1$s", "了"));// 设置参数成功，case: 开始 了
        System.out.println(String.format("开始%2$s%1$s%3$s", "吗", "了", "呢"));// 设置参数成功，case: 开始了吗呢
        System.out.println(String.format("你好 %s 吗?", "开心"));
        System.out.println(String.format("测试 %03d", 1) );
        System.out.println(String.format("测试 %s%s", "Hell", "o") );
        System.out.println(String.format("$#$测试 %s%s", "Hell", "o") );
    }

    /**
     * 去掉首尾两端的空格
     */
    @Test
    @Disabled
    void trimTest() {
        String expectStr = "fff a w b";
        String error1 = " fff a w b ";
        String error2 = " \nfff a w b \n";
        System.out.println(error1.trim());
        System.out.println(expectStr.equals(error1.trim()));
        System.out.println(error2.trim());
        System.out.println(expectStr.equals(error2.trim()));
    }
    
    /**
     * 测试+null值
     */
    @Test
    @Disabled
    void equalTest() {
        String o = null;
        if (true) {
            o = "b";
        }
        String a = "ab";
        System.out.println("a" + "b" == a);// print: false
        System.out.println("a" + o == a);// print: false
    }
    

    /**
     * 测试String.valueOf
     */
    @Test
    @Disabled
    void valueOf() {
        Object obj = null;
        String s = String.valueOf(obj);
        System.out.println(Objects.isNull(s));// pritln false
    }
    
    @Test
    @Disabled
    void charsetTest() throws UnsupportedEncodingException {
        String origin = "编码";
        System.out.println(new String(origin.getBytes()));
        System.out.println(new String(origin.getBytes("utf-8"), "utf-8"));
        System.out.println(new String(origin.getBytes("gbk"), "gbk"));
    }

    @Test
    @Disabled
    void unicodeTest() {
//        System.out.println("\u0001");
//        UnicodeBlock.AEGEAN_NUMBERS

    }
    
    // 测试脱敏
    @Test
    @Disabled
    void replaceAllTest() {
        String result = "1234556679901".replaceAll("(?<=^.{3}).", "*");
        System.out.println(deSensitization(result, 3, 4, '*'));
    }
    
    private String deSensitization(String original, int prefix, int suffix, char c) {
        char[] chars = original.toCharArray();
        int len = chars.length;
        for (int i = prefix; i < len && i < len - suffix; i++) {
            chars[i] = c;
        }
        return new String(chars);
    }
    
    @Test
    void castExceptionTest() {
//        System.out.println(Integer.valueOf("sdfsdf"));// java.lang.NumberFormatException: For input string: "sdfsdf"
        Object f = "fff";
        System.out.println((int) f);// java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        
    }
  
}
