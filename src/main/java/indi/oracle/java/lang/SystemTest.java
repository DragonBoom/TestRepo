package indi.oracle.java.lang;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(TestSeparateExtension.class)
@Slf4j
public class SystemTest {

    @Test
    @Disabled
    void sysoutTest() {
        System.out.println("yahello");
        System.out.close();
        System.out.println("yahello");
        
        log.debug("log yahello");// not print
        log.info("log yahello");
    }
    
    @Test
    void getPropertyTest() {
        System.out.println(System.getProperty("os.name"));// print Windows 10
        System.out.println(System.getProperty("os.name").startsWith("Win"));// print true
        String property = System.getProperty("onedrive");
        System.out.println(property);// case null
        property = System.getenv("onedrive");
        System.out.println(property);// case C:\Users\DragonBoom\OneDrive
        
        // os.version: 6  = win vista
        property = System.getProperty("os.version");// print: 6.3
        System.out.println(property);
        
        // java.net.preferIPv4Stack
        // 当os.version错误，或操作系统不支持 Dualstackplainsocketimpl（注释写着win系统版本必须大于vista(os.version=6.0)） 时，可用该属性强行使用ipv4？
        // DefaultDatagramSocketImplFactory preferIPv4Stack = Boolean.parseBoolean(System.getProperties().getProperty("java.net.preferIPv4Stack"));
        System.out.println(Boolean.parseBoolean(System.getProperty("java.net.preferIPv4Stack")));
        
    }
}
