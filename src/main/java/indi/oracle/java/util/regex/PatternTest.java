package indi.oracle.java.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PatternTest {

    /**
     * 
     * print \Q123fa\E
     */
    @Test
    @Disabled
    void quoteTest() {
        String str = "123fa";
        System.out.println(Pattern.quote(str));// \Q123fa\E
    }
    
    /**
     * 测试双引号转义
     */
    @Test
    @Disabled
    void doubleQuotationTest() {
        String s = "开始 \" u=468256973,4115862808&fm=26&gp=0.jpg";
        Matcher matcher = Pattern.compile("(?<!\\\\)\"").matcher(s);
        matcher.find();
        System.out.println(matcher.group());
    }
    
    /**
     * 测试用于匹配HTTP Range请求头的正则
     */
    @Test
    void HTTPRangeHeaderMatchTest() {
        String rangeHeader1  = "bytes=2482046-";
        String rangeHeader2  = "bytes=2482046-2482047";
        String regex = "bytes=\\d+-(\\d+|)";
        System.out.println(rangeHeader1.matches(regex));
        System.out.println(rangeHeader2.matches(regex));
        
    }
}
