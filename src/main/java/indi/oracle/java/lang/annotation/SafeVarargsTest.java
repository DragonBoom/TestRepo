package indi.oracle.java.lang.annotation;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SafeVarargsTest {

    @Test
    void go() {
        m(Arrays.asList("123", "245"));
    }

    @SafeVarargs // Not actually safe!
    static void m(List<String>... stringLists) {
        Object[] array = stringLists;
        List<Integer> tmpList = Arrays.asList(42);
        array[0] = tmpList; // Semantically invalid, but compiles without warnings
        String s = stringLists[0].get(0); // Oh no, ClassCastException at runtime!
    }
}
