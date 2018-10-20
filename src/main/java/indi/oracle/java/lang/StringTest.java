package indi.oracle.java.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class StringTest {

    //    @Test
    void char2StringTest() {

        String c = "(213_)]";
        Pattern p = Pattern.compile("[\\w\\[\\]\\-\\(\\)]");
        Matcher m = p.matcher(c);
        int count = 0;
        while (m.find()) {
            count++;
        }
        System.out.println(count);
    }

    // @Test
    void spilitTest() {
        String str = "开始";
        for (String ele : str.split("\\W")) {
            System.out.println(ele);
        }
    }

    void constructorTest() {
        String[] strs = new String[] { "123", "123" };
    }
}
