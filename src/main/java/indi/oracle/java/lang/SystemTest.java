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
        String property = System.getProperty("onedrive");
        System.out.println(property);// case null
        property = System.getenv("onedrive");
        System.out.println(property);// case C:\Users\DragonBoom\OneDrive
    }
}
