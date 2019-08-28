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
        System.out.println(Pattern.quote(str));
    }
    
    /**
     * 身份证读卡器为用户名后缀附加的乱码
     * 
     * @since 2019.08.08
     */
    @Test
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
}
