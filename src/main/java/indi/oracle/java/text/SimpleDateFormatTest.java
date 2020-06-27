package indi.oracle.java.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * {@link java.text.DateFormatSymbols}
 */
class SimpleDateFormatTest {

    private static final SimpleDateFormat helper = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 测试 yyy 模板能否解析字符串，会有什么效果
     */
//    @Test
    void test1() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
        Date date = format.parse("0180919");
        System.out.println(helper.format(date));// 0018-09-19
    }

    /**
     * 测试如何在模板中插入无关的字符串
     */
//    @Test
    void test2() throws ParseException {
        String str = "011y 11mo 11w 11d 11h 11m 11s";
        SimpleDateFormat format = new SimpleDateFormat("yyyy'y' MM'mo' WW'w' dd'd' HH'h' mm'm' ss's'");
        Date date = format.parse(str);

        System.out.println(helper.format(date));// 0011-11-11
    }

    /**
     * SimpleDateFormat 线程不安全?
     * @throws ParseException 
     */
    void test3ForDebug() throws ParseException {
        // TODO 看SimpleDateFormat的算法
    }
    
    
}
