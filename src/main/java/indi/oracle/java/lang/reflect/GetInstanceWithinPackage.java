package indi.oracle.java.lang.reflect;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class GetInstanceWithinPackage {

    private static final String PACKAGE_NAME = "indi/oracle/java/reflect";

    @Test
    void delimiter1() throws ClassNotFoundException, IOException {
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                .getResources(PACKAGE_NAME);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);// case : file:/E:/github/TestRepo/target/classes/indi/oracle/java/reflect
        }
    }
    
    @Test
    void delimiter2() throws ClassNotFoundException, IOException {
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                .getResources("indi.oracle.java.reflect");
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);// do nothing
        }
    }
}
