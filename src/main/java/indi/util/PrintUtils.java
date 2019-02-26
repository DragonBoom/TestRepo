package indi.util;

import java.util.Arrays;

public class PrintUtils {

    public static String print(String toPrint) {
        System.out.println(toPrint);
        return toPrint;
    }
    
    public static String print(Object obj) {
        return obj == null ? print("null") : print(obj.toString());
    }
    
    public static void printInts(int[] ints) {
        System.out.println(Arrays.toString(ints));
    }
}
