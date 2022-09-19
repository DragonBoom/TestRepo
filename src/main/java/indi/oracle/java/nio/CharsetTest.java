/**
 * 
 */
package indi.oracle.java.nio;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * @author wzh
 * @since 2020.09.24
 */
@ExtendWith(TestSeparateExtension.class)
class CharsetTest {

    @Test
    @Disabled
    void supportCharsetTest() {
        Assertions.assertNotNull(Charset.forName("gbk"));
    }
    
    @Test
    @SneakyThrows
    void chaosConvert() {
        // 原乱码字符串字面量->按gbk解码->按sjis编码=正确字符串
        String str = new String("徯夘夋憸偺奼戝斉乮1120亊840px乯".getBytes("gbk"), "sjis");
        System.out.println(str);
    }
    
}
