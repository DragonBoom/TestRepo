package indi.oracle.java.net;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class URITest {

    @Test
    void getRawSchemeTest() {
        String uriStr = "redis://@172.104.66.71:6380?password=!qQ1312449403";
        URI uri = URI.create(uriStr);
        System.out.println(uri);
        System.out.println(uri.getScheme());
        System.out.println(uri.getQuery());
        System.out.println(uri.getRawQuery());
        System.out.println(uri.getRawSchemeSpecificPart());
        
    }
}
