package indi.mockito;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * for org.mockito.Mockito
 * 
 * @author wzh
 * @since 2019.12.16
 */
class MokitoTest {
    
    @Test
    void go() {
        /*
         * 第一次用模拟工具，测试一下怎么用
         */
        LinkedList<String> mockedList = Mockito.mock(LinkedList.class);
        Mockito.when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0));
    }

}
