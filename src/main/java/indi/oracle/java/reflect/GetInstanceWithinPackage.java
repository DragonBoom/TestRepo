package indi.oracle.java.reflect;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class GetInstanceWithinPackage {

    private static final String PACKAGE_NAME = "indi/oracle/java/reflect";

    @Test
    void go() throws ClassNotFoundException, IOException {
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                .getResources(PACKAGE_NAME);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }
    }
}
