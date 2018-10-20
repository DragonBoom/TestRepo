package indi.mockito;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SimpleTest {
    
    @Test
    void go() {
        LinkedList<String> mockedList = Mockito.mock(LinkedList.class);
        Mockito.when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0));
    }

}
