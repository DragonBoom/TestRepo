/**
 * 
 */
package indi.apache.commons.lang;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

/**
 * @author wzh
 * @since 2021.01.27
 */
@ExtendWith(TestSeparateExtension.class)
class StringEscapeUtilsTest {
    String unicodeString = "\\u8be5\\u4f5c\\u54c1\\u5df2\\u88ab\\u5220\\u9664\\uff0c\\u6216\\u4f5c\\u54c1ID\\u4e0d\\u5b58\\u5728\\u3002";
    
    // unicode String -> String
    @Test
    void unescapeUnicodeTest() {
        String str = StringEscapeUtils.unescapeJava(unicodeString);
        System.out.println(str);
        Assertions.assertEquals("该作品已被删除，或作品ID不存在。", str);
    }

}
