package indi.oracle.java.time.format;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateTimeFormatterTest {
    String basicPattern = "yyyy MM dd HH mm ss";
    SimpleDateFormat sdf = new SimpleDateFormat(basicPattern);
    
    String pattern = "yyyy-MM-dd - hh";// error because 'hh'
    String str = "2018-11-02 - hh";// error because 'hh'

    String pattern2 = "yyyy==MM==dd HH时mm分";
    String str2 = "2014==04==12 01时06分";

    /**
     * 如何用DateTimeFormatter得到Date
     * 
     * <p>考虑到该过程还是比较麻烦，只有线程不安全情况下才考虑使用；
     * 不过如果只是要拿到日期字符串，可以优先考虑用java.time下的类
     */
    @Test
    void parseTest2() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern2);
        
        Instant instant = LocalDateTime
                .parse(str2, dateTimeFormatter)// 解析字符串
                .toInstant(ZoneOffset.ofHours(8));// 时区
        
        /*
         * ps. LocalDate 只含日期，LocalTime不含日期
         */
        Date date2 = Date.from(instant);
        
        String format = sdf.format(date2);
        
        Assertions.assertEquals("2014 04 12 01 06 00", format);
    }
    
    private final static DateTimeFormatter CRON_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ss mm HH dd MM ? yyyy");
    
    private String cron = "39 30 15 22 11 ? 2018";
    
    /**
     * 
     */
    @Test
    void cronTest() {
        LocalDateTime localDateTime = LocalDateTime.parse(cron, CRON_DATE_TIME_FORMATTER);
        System.out.println(localDateTime);
    }
}
