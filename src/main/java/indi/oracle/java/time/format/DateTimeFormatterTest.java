package indi.oracle.java.time.format;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class DateTimeFormatterTest {
    String basicPattern = "yyyy MM dd HH mm ss";
    SimpleDateFormat sdf = new SimpleDateFormat(basicPattern);
    
    String pattern = "yyyy-MM-dd - hh";// error because 'hh'
    String str = "2018-11-02 - hh";// error because 'hh'

    String pattern2 = "yyyy==MM==dd HH时mm分";
    String str2 = "2014==04==12 01时06分";

    /**
     * 测试：如何用DateTimeFormatter得到Date
     * 
     * <p>结论：过程比较麻烦，只有线程不安全情况下才考虑使用；
     * 不过如果只是要拿到日期字符串，可以优先考虑用java.time下的类
     */
    @Test
    @Disabled
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
    @Disabled
    void cronTest() {
        LocalDateTime localDateTime = LocalDateTime.parse(cron, CRON_DATE_TIME_FORMATTER);
        System.out.println(localDateTime);
    }
    
    @Test
    @Disabled
    void parsetTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'['HH:mm:ss.SSS']'");// 使用单引号转义
        TemporalAccessor accessor = formatter.parse("[00:00:01.100]");// 毫秒必须3位
        LocalTime time = accessor.query(LocalTime::from);
        System.out.println(time);
        Assertions.assertEquals("00:00:01.100", time.toString());
    }
    
    /**
     * 可通过Builder创建灵活的模板（可设置模板无法实现的宽度、默认值等效果）
     * 
     * <p>定义过程较麻烦，但效果不错
     * 
     * @since 2020.09.19
     */
    @Test
    void builderTesT() {
        // 以下测试实现lrc的时间模板：[mm:ss.SSS]，改模板不含24小时，无法直接用LocalTime解析
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)// 可以此设置默认值
            .appendLiteral("[")
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .appendLiteral(".")
            .appendValue(ChronoField.MILLI_OF_SECOND, 2, 3, SignStyle.NEVER)
            .appendLiteral("]")
            .toFormatter();
        
        LocalTime localTime = LocalTime.parse("[00:00.009]", formatter);
        System.out.println(localTime);
        
        Assertions.assertEquals(0, localTime.getSecond());
        Assertions.assertEquals(9, localTime.getLong(ChronoField.MILLI_OF_SECOND));
    }
}
