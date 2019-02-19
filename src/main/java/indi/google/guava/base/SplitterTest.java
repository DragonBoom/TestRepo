package indi.google.guava.base;

import org.junit.jupiter.api.Test;

import com.google.common.base.Splitter;

public class SplitterTest {
    private static final String toTest = "粤ABD645、粤ABQ133、闽ACA745、";

    @Test
    void go() {
        Iterable<String> split = Splitter.on("、").split(toTest);
        for (String str : split) {
            System.out.println(str);
        }
        
    }
}
