package indi.oracle.java.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

public class DateTest {

    // @Test
    void getCurrentDateTest() {
        long millis = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String result = format.format(new Date(millis));
        System.out.println(result);
    }
    
    // @Test
    void simpleDate() {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
    }
    
    // @Test
    void dateFormatTest() {
        System.out.println(new Date());
        System.out.println(new java.sql.Date(System.currentTimeMillis()));
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
    }
    
    // @Test
    void localDateTest() {
        String code = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                + "SCH"
                + String.format("%05d", 123);
        System.out.println(code);
    }
    
    @Test
    void dateCompare() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        
        System.out.println(date);
        System.out.println(calendar.getTime());

        System.out.println(date.compareTo(calendar.getTime()));
    }
}
