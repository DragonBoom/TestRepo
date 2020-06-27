package indi.oracle.java.lang;

import org.junit.jupiter.api.Test;

public class TypeTest {

    /**
     * 测试泛型类类型转化的一些问题
     * <p>
     * 该方法跑得通...
     */
    @Test
    void go() {
        MyClass<String> myClass = new MyClass<String>();
        myClass.setObj("ww");
        MyClass<?> str = myClass;
        MyClass<Integer> integer = (MyClass<Integer>) str;
        System.out.println(integer.getObj());// 若再调用getClass方法就会报错
    }

    public static class MyClass<T> {
        T obj;

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }

        public void clear() {
            obj = null;
        }
    }
}
