package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;

public class StringTest {

    // @Test
    void splitTest() {
        String s = new String("www|fff|www");
        String[] afterSplit = s.split("\\|");
        for (String str : afterSplit) {
            System.out.println(str);
        }
    }
    
    // @Test
    void formatTest() {
        String s = new String("wwwww\nwwww");
        System.out.println(s);
        System.out.println(s.replace("\n", ""));
    }
    
    /**
     * 换行测试
     */
    @Test
    void newLineTest() {
        String x = "a\nf";
        
        System.out.println(x.indexOf("\n"));
        System.out.println(x);
    }
}
