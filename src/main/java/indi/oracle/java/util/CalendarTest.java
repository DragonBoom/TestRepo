package indi.oracle.java.util;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

public class CalendarTest {

    @Test
    void go() {
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));// 从0开始!!
        System.out.println(calendar.get(Calendar.DATE));
    }
    
}
