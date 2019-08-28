package indi.oracle.java.util;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class ArraysTest {

    @Test
    void searchTest() {
        String[] strs = new String[] {"ff", "ww"};
        
        System.out.println(Arrays.binarySearch(strs, "ff"));// print: 0
        System.out.println(Arrays.binarySearch(strs, "ff2"));// print: -2
        System.out.println(Arrays.binarySearch(strs, "ffw"));// print: -2
    }
}
