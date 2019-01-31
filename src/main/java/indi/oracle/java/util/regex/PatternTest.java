package indi.oracle.java.util.regex;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class PatternTest {

    /**
     * 
     * print \Q123fa\E
     */
    @Test
    void quoteTest() {
        String str = "123fa";
        System.out.println(Pattern.quote(str));
    }
}
