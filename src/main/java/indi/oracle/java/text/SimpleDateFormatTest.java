package indi.oracle.java.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class SimpleDateFormatTest {

    @Test
    void go() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd");
        Date date = format.parse("20180919");
        System.out.println(format.format(new Date()));
        
    }
}
