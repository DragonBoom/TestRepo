package indi.oracle.java.util.concurrent;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeUnitTest {

    // @Test
    void daysTest() {
        long aDay = TimeUnit.DAYS.toMillis(1);
        long aDay2 = 24 * 60 * 60 * 1000;
        System.out.println(aDay);
        System.out.println(aDay2);
        Assertions.assertEquals(aDay, aDay2);
    }
    
//    @Test
    void getEnumByStringTest() {
        System.out.println(TimeUnit.valueOf("HOURS"));
        System.out.println(TimeUnit.valueOf("SECONDS"));
    }
    
//    @Test
    void negativeTest() {
        long millis = TimeUnit.MILLISECONDS.toMillis(-123L);
        System.out.println(millis);
    }
    
    @Test
    void convertTest() {
        long days = TimeUnit.MILLISECONDS.toDays(1000 * 60 * 60 * 37L); /* 24 < 37 < 48*/
        System.out.println(days);// = 1 即不满2天则计一天
    }
}
