package indi.oracle.java.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class Base64Test {

    @Test
    void encodeTest() {
        Encoder encoder = Base64.getUrlEncoder();
        String str = "https://10.194.252.56:8083/WebDiskServerDemo-doc?doc_id=123";
        String encodedStr = encoder.encodeToString(str.getBytes());
        System.out.println(encodedStr);
        Assertions.assertEquals("aHR0cHM6Ly8xMC4xOTQuMjUyLjU2OjgwODMvV2ViRGlza1NlcnZlckRlbW8vZG9jP2RvY19pZD0xMjM=", encodedStr);
        Encoder urlEncoder = Base64.getUrlEncoder();
    }
}
