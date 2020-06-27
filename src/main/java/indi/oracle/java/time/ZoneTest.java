package indi.oracle.java.time;

import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class ZoneTest {

    /*
     * ZoneOffset 是 ZoneId 的子类，因此ZoneOffset会有继承自ZoneId的静态方法
     */

    @Test
    void zoneOffsetTest() {
        ZoneOffset utc = ZoneOffset.UTC;
        ZoneOffset.ofHours(8);// => utf + 8 => 北京时间
        System.out.println(ZoneOffset.ofHours(8));// print: +08:00
    }

}
