package indi.oracle.java.util;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class Base64Test {

    // aim :aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8vZG9jP2RvY19pZD0xMjM=
    //      aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8tZG9jP2RvY19pZD0xMjM=
    //      aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8tZG9jP2RvY19pZD0xMjM=
    // 不管用那种，都会把斜杠转化为横杠
    @Test
    void encodeTest() {
        Encoder encoder = Base64.getEncoder();// UrlEncoder会将base64码进行转义比如斜杠转下划线
        String str = "https://10.194.252.56:8083/WebDiskServerDemo/doc?doc_id=123";
//        String str = "http://10.194.252.56:8083/WebDiskServerDemo/doc?doc_id=a8ef3808-a482-11e9-95c4-d00d80981a7e";
        String encodedStr = encoder.encodeToString(str.getBytes());
        System.out.println(encodedStr);
        // aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8tZG9jP2RvY19pZD0xMjM=
        // aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8vZG9jP2RvY19pZD0xMjM=
        System.out.println(new String(Base64.getDecoder().decode("aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8tZG9jP2RvY19pZD0xMjM=")));
        Assertions.assertEquals("aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8vZG9jP2RvY19pZD0xMjM=",
                encodedStr);
//        Encoder urlEncoder = Base64.getUrlEncoder();
    }
    
    @Test
    void decodeTest() {
        /*
         * 原base64: aHR0cDovLzEwLjE5NC4yNTIuNTY6ODA4My9XZWJEaXNrU2VydmVyRGVtby9kb2M/ZG9jX2lkPWE4ZWYzODA4LWE0ODItMTFlOS05NWM0LWQwMGQ4MDk4MWE3ZQ==
         * 这里可以发现，斜杠左、右两边的字符串，可以拆出来单独解码
         */
        String base64Str = "aHR0cDovLzEwLjE5NC4yNTIuNTY6ODA4My9XZWJEaXNrU2VydmVyRGVtby9kb2M";
        System.out.println(new String(Base64.getDecoder().decode(base64Str)));// case https://10.194.252.56:8083/WebDiskServerDemo/doc?doc_id=123

        base64Str = "ZG9jX2lkPWE4ZWYzODA4LWE0ODItMTFlOS05NWM0LWQwMGQ4MDk4MWE3ZQ==";
        System.out.println(new String(Base64.getDecoder().decode(base64Str)));// case https://10.194.252.56:8083/WebDiskServerDemo/doc?doc_id=123
    }
    
}
