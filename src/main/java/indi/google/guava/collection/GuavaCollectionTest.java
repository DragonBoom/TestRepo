package indi.google.guava.collection;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
class GuavaCollectionTest {

    @Test
    void listsTest() {
        ArrayList<String> arrayList = com.google.common.collect.Lists.newArrayList("f");
        System.out.println(arrayList);
    }
    
    @Test
    void immutableCollectionTest() {
        ImmutableList<String> immutableList = ImmutableList.of("fff");
        System.out.println(immutableList);
    }
}
