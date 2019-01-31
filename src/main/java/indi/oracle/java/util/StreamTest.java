package indi.oracle.java.util;

import java.util.LinkedList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class StreamTest {

    @Test
    void start() {
        LinkedList<Object> l = new LinkedList<>();
        l.add("www");
        l.add("wwwf");
        l.add("wwwff");
        l.add("wwwfff");
        l.stream()
            .filter(obj -> {
                System.out.println(obj);
                return true;
            })
            .map(obj -> {
                System.out.println(obj);
                return obj;
            })
            .close();

    }
}
