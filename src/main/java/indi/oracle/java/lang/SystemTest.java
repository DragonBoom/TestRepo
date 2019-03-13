package indi.oracle.java.lang;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(TestSeparateExtension.class)
@Slf4j
public class SystemTest {

    @Test
    void sysoutTest() {
        System.out.println("yahello");
        System.out.close();
        System.out.println("yahello");
        
        log.debug("log yahello");// not print
        log.info("log yahello");
    }
}
