/**
 * 
 */
package indi.oracle.java.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

/**
 * @author wzh
 * @since 2020.09.19
 */
@ExtendWith(TestSeparateExtension.class)
class LocalTimeTest {
    
    @Test
    void parseTest() {
        // 必须包含标准时间单位：24时、分、秒
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");// thead-safe, immutable
        System.out.println(LocalTime.parse("10:11:22", formatter));
    }

}
