package indi.oracle.java.util;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class HashCodeTest {

    @Test
    void date2HashTest() throws InterruptedException {
        Date date = new Date();
        int hash1 = date.hashCode();
        System.out.println(Integer.toUnsignedString(hash1));
        Thread.sleep(1000);
        
        Date date2 = new Date();
        int hash2 = date2.hashCode();
        System.out.println(Integer.toUnsignedString(hash2));
    }
}
