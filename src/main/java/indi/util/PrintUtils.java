package indi.util;

public class PrintUtils {

    public static String print(String toPrint) {
        System.out.println(toPrint);
        return toPrint;
    }
    
    public static String print(Object obj) {
        return obj == null ? print("null") : print(obj.toString());
    }
}
