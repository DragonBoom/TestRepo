package indi.oracle.java.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Assertions;
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
        
        // 不管调用什么方法，最终都会进入invoke方法
        System.out.println("---");
        proxyInstance.toString();
        System.out.println("---");
        proxyInstance.run();
        // 动态代理类只是其所实现接口的实现类，而与其代理实例（实现提供方）无关
        Assertions.assertFalse(proxyInstance instanceof MyInvocationHandler);
        Assertions.assertTrue(proxyInstance instanceof Runnable);
        Assertions.assertTrue(proxyInstance instanceof Comparable);
        
    }
    
    public static class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("yahello");
            return null;
        }
        
    }
}

