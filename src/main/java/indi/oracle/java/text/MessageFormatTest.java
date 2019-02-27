package indi.oracle.java.text;

import java.text.MessageFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class MessageFormatTest {

    @Test
    void go() {
        String pattern = "{0}, {1}, {2}";
        Object[] params = new String[] {"a", "b", "c"};
        String result = MessageFormat.format(pattern, params);
        
        System.out.println(result);// cause a, b, c
    }
}
