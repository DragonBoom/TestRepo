package indi.oracle.java.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

class ZoneTest {

    /*
     *ZoneOffset 是 ZoneId 的子类，因此ZoneOffset会有继承自ZoneId的静态方法 
     */
    
    @Test
    void zoneOffsetTest() {
        ZoneOffset utc = ZoneOffset.UTC;
        ZoneOffset.ofHours(8);// => utf + 8 => 北京时间
    }

}
