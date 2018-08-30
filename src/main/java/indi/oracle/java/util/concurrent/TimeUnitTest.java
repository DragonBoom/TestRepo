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
    
    @Test
    void getEnumByStringTest() {
        System.out.println(TimeUnit.valueOf("HOURS"));
        System.out.println(TimeUnit.valueOf("SECONDS"));
    }
}
