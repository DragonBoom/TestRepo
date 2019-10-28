package indi.apache.commons.time;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class DateUtilsTest {
    
    @Test
    void a() {
        // 占位，确保计算其他测试方法时已加载类
    }

    /**
     * getFragment 应该是用于获取目标时间单位从0点到指定时间的特定时间单位的长度
     */
    @Test
    void getFragmentTest() {
//        long r1 = DateUtils.getFragmentInHours(new Date(), Calendar.HOUR);// IllegalArgumentException:The fragment 10 is not supported
//        System.out.println(r1);
        
        // 获取从今天0点到现在经过的小时
        long r2 = DateUtils.getFragmentInHours(new Date(), Calendar.DAY_OF_MONTH);
        System.out.println(r2);
    }
    
    // 测试时间（都是0millis）
    @Test
    void getHoursTest() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
    }
}
