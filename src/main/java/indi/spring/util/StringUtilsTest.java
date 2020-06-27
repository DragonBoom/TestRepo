package indi.spring.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.util.StringUtils;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class StringUtilsTest {

    @Test
    void cleanTest() {
        String result = StringUtils.cleanPath("file:\\e:/a.test");
        System.out.println(result);// print: file:/e:/a.test
    }
}
