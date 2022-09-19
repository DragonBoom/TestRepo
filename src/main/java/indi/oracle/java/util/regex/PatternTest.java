package indi.oracle.java.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
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
    @Disabled
    void HTTPRangeHeaderMatchTest() {
        String rangeHeader1  = "bytes=2482046-";
        String rangeHeader2  = "bytes=2482046-2482047";
        String regex = "bytes=\\d+-(\\d+|)";
        System.out.println(rangeHeader1.matches(regex));
        System.out.println(rangeHeader2.matches(regex));
        
    }
    
    /**
     * 身份证读卡器为用户名后缀附加的乱码
     * 
     * @since 2019.08.08
     */
    @Test
    @Disabled
    void rareStringPatternTest() {
        String name = "努尔麦麦提·卡卡西";
        // 匹配非英文、中文、数字、下划线、（全角/半角）点、数字
        // 匹配白名单从左到右为：中文，数字，小写字母，大写字母，井号（*），“·”，下划线，空格
        String pattern = "[^(\u4e00-\u9fa5)(\\d)(a-z)(A-Z)\\*·_\\s]*?$";
        Matcher matcher = Pattern.compile(pattern).matcher(name);
        matcher.find();
        String founded = matcher.group();
        System.out.println(founded);
        System.out.println(founded.length());
        System.out.println(name.replaceAll(pattern, ""));
        // 正则的 \s 无法匹配\u0000 !!!
    }
    
    @Test
    @Disabled
    void uriParseTest() {
        String url = "https://konachan.com/post/show/292249/am1m-animal_ears-brave_girl_ravens-breasts-cleavag";
        Matcher matcher = Pattern.compile("(?<=/)\\d+?(?=/)").matcher(url);
        System.out.println(matcher.find());
        System.out.println(matcher.group());
    }
    
    @Test
    void regexpTest() {
        test("(?<=\\.)[^\\.]+$", "d:\\f\\CCF20140611_00004");
        test("[^\\[\\]\\d]", "[by:PLASTICMEMORIES_]");
    }
    
    /**
     * 快速且可批量地测试匹配正则
     */
    private void test(String pattern, String... texts) {
        for (String text : texts) {
            Matcher matcher = Pattern.compile(pattern).matcher(text);
            String matchR = null;
            if (matcher.find()) {
                matchR = matcher.group();
                System.out.println(pattern);
                System.out.println("  " + text + "  --get-->  |" + matchR);
                System.out.println("----匹配成功----");
            } else {
                System.out.println(pattern);
                System.out.println("----匹配失败----");
//                Assertions.fail(text);
            }
        }
    }
}

