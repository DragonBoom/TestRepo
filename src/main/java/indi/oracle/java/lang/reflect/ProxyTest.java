package indi.oracle.java.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.util.extension.TestSeparateExtension;

@ExtendWith(TestSeparateExtension.class)
public class ProxyTest {

    @Test
    void go() {
        MyInvocationHandler ih = new MyInvocationHandler();
        Class<?>[] classes = {Runnable.class, Comparable.class};
        Runnable proxyInstance = (Runnable) Proxy.newProxyInstance(this.getClass().getClassLoader(), classes, ih);
        System.out.println(proxyInstance);
        System.out.println("name: " + proxyInstance.getClass().getName());
        proxyInstance.toString();
        proxyInstance.run();
        
    }
    
    public static class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("yahello");
            return null;
        }
        
    }
}

