/**
 * 
 */
package indi.oracle.java.time;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

/**
 * @author wzh
 * @since 2020.09.19
 */
@ExtendWith(TestSeparateExtension.class)
class DurationTest {

    @Test
    @Disabled
    void parsetTest() {
        Duration duration = Duration.parse("pt1M1.001s");// 注意，这里的001S不等于1S（1S=100S）；大小写不敏感
        System.out.println(duration);
        Assertions.assertEquals(61001L, duration.toMillis());
        
        duration = Duration.parse("p1m1dt24h");// 注意，这里的001S不等于1S；大小写不敏感
        System.out.println(duration);
        Assertions.assertEquals(33, duration.toDays());
    }
    
    @Test
    void convertTest() {
        // 通过给定两个时间，从LocalTime获取Duration，这应该是最直观的创建方式了
        Duration duration = Duration.between(LocalTime.MIN, LocalTime.parse("10:15"));// 注意，这里返回值的正负受顺序影响
        Assertions.assertEquals(615, duration.toMinutes());
        
        // Duration -> LocalTime
        LocalTime localTime = LocalTime.ofNanoOfDay(duration.toNanos());
        Assertions.assertEquals("10:15", localTime.toString());
    }
}
