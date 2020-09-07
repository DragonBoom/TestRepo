package indi.oracle.java.lang.reflect;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.system.ApplicationHome;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class GetPathTest {
    
    /**
     * 获取Jar包的绝对地址
     * 
     * @throws UnsupportedEncodingException 
     */
    @Test
    void getPath() throws UnsupportedEncodingException {
        String file = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        System.out.println(file);// print: /E:/github/TestRepo/target/classes/
        String path = URLDecoder.decode(file, "utf-8");
        System.out.println(path);// print: /E:/github/TestRepo/target/classes/
        System.out.println(new File(path));// print: E:\github\TestRepo\target\classes
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        System.out.println(applicationHome.getSource());// print: null
    }

}
