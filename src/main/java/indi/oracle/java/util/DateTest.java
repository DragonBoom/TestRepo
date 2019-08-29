package indi.oracle.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
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
    
//    @Test
    void dateCompare() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        
        System.out.println(date);
        System.out.println(calendar.getTime());

        System.out.println(date.compareTo(calendar.getTime()));
    }
    
    // @Test
    void dateCompare2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date parse1 = sdf.parse("20180912");
        Date parse2 = sdf.parse("20180913");
        System.out.println(parse1.compareTo(parse2));
    }
    
    @Test
    @Disabled
    void dateCompare3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date parse1 = sdf.parse("20180912");
        Date parse2 = sdf.parse("20180913");
        System.out.println(parse1.before(parse2));
    }
    
    /**
     * getTime得到的值，实际是不包含时区信息的
     */
    @Test
    @Disabled
    void getTimeTest() throws ParseException {
        long time = new SimpleDateFormat("yyyyMMdd hh mm ss").parse("20190101 17 00 00").getTime();
        long millis = time % TimeUnit.DAYS.toMillis(1);
        System.out.println(millis);
        System.out.println(TimeUnit.MILLISECONDS.toHours(millis));// case: 9

        // 测试 小于8的时间
        long time2 = new SimpleDateFormat("yyyyMMdd hh mm ss").parse("20190101 5 00 00").getTime();
        long millis2 = time2 % TimeUnit.DAYS.toMillis(1);
        System.out.println(millis2);
        System.out.println(TimeUnit.MILLISECONDS.toHours(millis2));// case: 21 = (5 - 8 + 24)
    }

    @Test
    @Disabled
    void toStringTest() {
        System.out.println(new Date());
    }
    
    @Test
    void newByMillisTest() {
        Date date = new Date(1564547030000L);
        System.out.println(date);
    }
    
}
