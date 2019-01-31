package indi.oracle.java.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;

public class CalendarTest {

    /**
     * 测试计算日期的时候跳过周末（即对于日期，除了本身就处于周末外，每经过一次周末就 +2 天）<br>
     * 
     * @throws ParseException
     */
    @Test
    void skipWeekend() {
        TimeUnit unit = TimeUnit.HOURS;
        long duration = 24 * 3;
        // 将时间转化为毫秒
        long millis = unit.toMillis(duration);
        Date beginDate = new Date();// 起始日期
        Date predictDate = new Date(beginDate.getTime() + millis);// 不避开周末的超时日期

        Calendar predictCalendar = Calendar.getInstance();// 避开周末的预测超时日期
        predictCalendar.setTime(predictDate);
        
        Date saturday = findMinSaturday(beginDate);
        
        Calendar saturdayCalendar = Calendar.getInstance();// 当周周六日期
        saturdayCalendar.setTime(saturday);
        /*
         *  从起始日期开始，对于每周，只要预测超时日期大于或等于当周周六，预测超时时间就 + 2天
         */
        while (predictCalendar.after(saturdayCalendar) || predictCalendar.equals(saturdayCalendar)) {
            predictCalendar.add(Calendar.DATE, 2);
            saturdayCalendar.add(Calendar.DATE, 7);
        }
        
        // final 返回避开周末的超时日期
        System.out.println(predictCalendar.getTime());
    }
    
    // 找到当周的周6
    private Date findMinSaturday(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 将日期设为周六
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY + 5 - dayOfWeek);
        // 将时分秒均设为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    
    ImmutableMap<String, BiConsumer<String, Calendar>> helper = ImmutableMap.<String, BiConsumer<String, Calendar>>builder()
            .put("y", (time, calendar) -> calendar.add(Calendar.YEAR, Integer.parseInt(time.substring(0, time.length() - 1))))
            .put("o", (time, calendar) -> calendar.add(Calendar.MONTH, Integer.parseInt(time.substring(0, time.length() - 2))))
            .put("w", (time, calendar) -> calendar.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(time.substring(0, time.length() - 1))))
            .put("d", (time, calendar) -> calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(time.substring(0, time.length() - 1))))
            .put("h", (time, calendar) -> calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, time.length() - 1))))
            .put("m", (time, calendar) -> calendar.add(Calendar.MINUTE, Integer.parseInt(time.substring(0, time.length() - 1))))
            .put("s", (time, calendar) -> calendar.add(Calendar.SECOND, Integer.parseInt(time.substring(0, time.length() - 1))))
            .build();
    
//    @Test
    void parseStrTest() {
        Date createDate = new Date();
        String str = "0y 0mo 0w 0d 1h 11m 11s";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        // 切割字符串
        String[] split = str.split(" ");
        
        for (String s : split) {
            String substring = s.substring(s.length() - 1);
            BiConsumer<String, Calendar> biConsumer = helper.get(substring);
            if (biConsumer != null) {
                biConsumer.accept(s, calendar);
            }
        }
        
//        time = split[0].substring(0, split[0].length() - 1);
//        calendar.add(Calendar.YEAR, Integer.parseInt(time));
//        time = split[1].substring(0, split[1].length() - 2);
//        calendar.add(Calendar.MONTH, Integer.parseInt(time));
//        time = split[2].substring(0, split[2].length() - 1);
//        calendar.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(time));
//        time = split[3].substring(0, split[3].length() - 1);
//        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(time));
//        time = split[4].substring(0, split[4].length() - 1);
//        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(time));
//        time = split[5].substring(0, split[5].length() - 1);
//        calendar.add(Calendar.MINUTE, Integer.parseInt(time));
//        time = split[6].substring(0, split[6].length() - 1);
//        calendar.add(Calendar.SECOND, Integer.parseInt(time));
        System.out.println(calendar.getTime());
    }
}
