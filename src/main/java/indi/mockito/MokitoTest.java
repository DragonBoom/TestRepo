package indi.mockito;

import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import indi.util.TestObjects;
import indi.util.TestObjects.Plain1ArgObj;

/**
 * for org.mockito.Mockito {@link https://site.mockito.org/}
 * 
 * @author wzh
 * @since 2019.12.16
 */
class MokitoTest {
    
    @Test
    @Disabled
    void first() {
        LinkedList<String> mockedList = Mockito.mock(LinkedList.class);// well
        Mockito.when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0));
    }
    
    @Test
    void mockObjectGet() {
        Plain1ArgObj mockObj = Mockito.mock(TestObjects.Plain1ArgObj.class);
        System.out.println(mockObj.getArg1());// print: null
        
        // a. 静态调用
        Mockito.when(mockObj.getArg1()).thenReturn("sb");
        // b. 在 import static org.mockito.Mockito.*; 否，不用声明静态类
        when(mockObj.getArg1()).thenReturn("sb");
        
        // c. 测试
//        when(mockObj.getArg1()).thenReturn(1);// compile error
        
        System.out.println(mockObj.getArg1());
    }
    

}
