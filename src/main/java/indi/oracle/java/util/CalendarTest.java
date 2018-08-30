package indi.oracle.java.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class CalendarTest {

    /**
     * 测试计算日期的时候避开工作日<br>
     * 5 + 7 - 5<br>
     * 6 + 7 - 6<br>
     * ...
     * 
     * @throws ParseException
     * 
     */
    @Test
    public void avoidWorkDateTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2018-08-25");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek >= 5) {
            int duration = 8 - dayOfWeek;
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.add(Calendar.DAY_OF_WEEK, duration);
        }
        System.out.println(calendar.getTime());
        // return calendar.getTime();
    }
}
