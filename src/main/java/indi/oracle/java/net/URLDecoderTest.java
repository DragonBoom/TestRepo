package indi.oracle.java.net;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class URLDecoderTest {
    String str1 = "fff中文测试123wa";
    
    @Test
    void go() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(str1, "utf-8");
        System.out.println(encode);// print: fff%E4%B8%AD%E6%96%87%E6%B5%8B%E8%AF%95123wa
        String decode = URLDecoder.decode(encode, "utf-8");
        System.out.println(decode);// print: fff中文测试123wa
    }

}
