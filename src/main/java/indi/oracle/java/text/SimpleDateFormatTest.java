package indi.oracle.java.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

/**
 * 更多可见：https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 * 
 * {@link java.text.DateFormatSymbols}
 */
@ExtendWith(TestSeparateExtension.class)
class SimpleDateFormatTest {
    // 线程不安全？
    private static final SimpleDateFormat helper = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:S");

    /**
     * 测试 yyy 模板能否解析字符串，会有什么效果
     */
    @Test
    void test1() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
        Date date = format.parse("0180919");
        System.out.println(helper.format(date));// 0018-09-19
    }

    /**
     * 测试如何在模板中插入无关的字符串（如何脱敏）：可通过单引号脱敏
     */
    @Test
    void test2() throws ParseException {
        String str = "011y 11mo 11w 11d 11h 11m 11s";
        SimpleDateFormat format = new SimpleDateFormat("yyyy'y' MM'mo' WW'w' dd'd' HH'h' mm'm' ss's'");
        Date date = format.parse(str);

        System.out.println(helper.format(date));// 0011-11-11
    }
    
    /**
     * 测试位数问题：符号写多少次表示低于多少位时补0；
     * 
     */
    @Test
    @Disabled
    void millisecondsTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("ss.S");
        String formated = sdf.format(new Date());
        System.out.println(formated);
        // 该sdf可用于将不足或超过位数的字符串转化为日期
        Date date1 = sdf.parse("11.1");
        Date date2 = sdf.parse("21.1");
        System.out.println(date1);
        System.out.println(helper.format(date1));
        System.out.println(helper.format(date2));
        Assertions.assertTrue(date1.compareTo(date2) < 0);
    }

    /**
     * SimpleDateFormat 线程不安全?
     * @throws ParseException 
     */
    void test3ForDebug() throws ParseException {
        // TODO 看SimpleDateFormat的算法
    }
    
    /**
     * 用z, Z, X表示时区 （其中，z和Z的作用似乎相同）
     * @throws ParseException
     * @since 2021.04.04
     */
    @Test
    void timeZoneTest() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse("2021-02-22T11:37:44+0900");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz").parse("2021-02-22T11:37:44GMT+09:00");
        Date date3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse("2021-02-22T11:37:44+09:00");
        System.out.println(date);
        
        Assertions.assertEquals(date, date2);
        Assertions.assertEquals(date2, date3);
    }
    
}
