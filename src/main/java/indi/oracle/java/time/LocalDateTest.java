package indi.oracle.java.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class LocalDateTest {

    @Test
    void test() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String format2 = LocalDate.now().format(DateTimeFormatter.ISO_DATE);// 
//        String format2 = LocalDate.now().format(DateTimeFormatter.ISO_DATE_TIME);// ERROR
        
        System.out.println(format);
        System.out.println(format2);
    }

}
