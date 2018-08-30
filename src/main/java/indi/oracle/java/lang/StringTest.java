package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    void splitTest() {
        String s = new String("www|fff|www");
        String[] afterSplit = s.split("\\|");
        for (String str : afterSplit) {
            System.out.println(str);
        }
    }
}
