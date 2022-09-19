/**
 * 
 */
package indi.oracle.java.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.test.TestSeparateExtension;

/**
 * @author wzh
 * @since 2020.09.19
 */
@ExtendWith(TestSeparateExtension.class)
class LocalDateTimeTest {
    
    @Test
    void parseTest() {
        /*
         * 必须包含以下单位（大小写敏感），少了一个都会抛异常：Unable to obtain LocalDateTime from TemporalAccessor:...
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");// thead-safe, immutable
        System.out.println(LocalDateTime.parse("2001-01-01 11:11:11", formatter));
    }

}
