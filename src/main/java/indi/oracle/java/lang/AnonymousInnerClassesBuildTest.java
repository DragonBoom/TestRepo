package indi.oracle.java.lang;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

public class AnonymousInnerClassesBuildTest {

    @Test
    void go() {
        LinkedList<Object> list = new LinkedList<> ();
        /**
         * will build AnonymousInnerClassesBuildTest$1.class
         * will build AnonymousInnerClassesBuildTest$2.class <3
         * will build AnonymousInnerClassesBuildTest$3.class
         */
        Consumer<String> consumer = obj -> {
            LinkedList<Object> list2 = new LinkedList<> ();
            list2.stream().map(ele -> {
                new HashMap() {};
                return new HashMap() {};
            });
            new HashMap() {};
            new HashMap() {};
        };
    }
}
