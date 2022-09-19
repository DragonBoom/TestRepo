package indi.oracle.java.lang;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
        System.out.println(String.format("%.0f", 1.2221f));// print: 1
        System.out.println(String.format("%+3d", 2));// print:   2 （数字前有两个空格）
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
        System.out.println(o == "b");// print: true
        System.out.println("c" == "c");// print: true
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
        System.out.println("fff".replace("fff", "[fff]"));
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
    @Disabled
    void castExceptionTest() {
//        System.out.println(Integer.valueOf("sdfsdf"));// java.lang.NumberFormatException: For input string: "sdfsdf"
        Object f = "fff";
        System.out.println((int) f);// java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        
    }
    
    // 空格也会被当作一节
    @Test
    @Disabled
    void splitTest() {
        String uploadPath = "/upload/vphall/201705/20170503/23";
        String targetPath = "";
        for (String folder : uploadPath.split("/")) {
            System.out.println(folder);
            targetPath = targetPath + "/" + folder;
            System.out.println(targetPath);
        }
    }
    
    // matches方法的正则匹配是否是全字匹配？ Y
    @Test
    @Disabled
    void matchesTest() {
        String str = "abcd";
        System.out.println(str.matches("a"));// false
    }
    
    @Test
    @Disabled
    void compateToTest() {
        // 测试能否数字化地进行比较: N
        System.out.println("11.jpg".compareTo("1.jpg"));
        System.out.println("11.jpg".compareTo("2.jpg"));
        System.out.println("img005.jpg".compareTo("img005_2.jpg"));
    }
    
    /**
     * 通过编码创建字符串
     * 
     * @since 2021.12.23
     */
    @Test
    void newStringByCodeTest() {
        // 𤭢，无法用字符字面量表示
        "𤭢".codePoints().forEach(System.out::println);
        int[] codes = new int[] {150370};
        String ns = new String(codes, 0, 1);
        System.out.println(ns);
        Assertions.assertEquals("𤭢", ns);
    }
  
}
