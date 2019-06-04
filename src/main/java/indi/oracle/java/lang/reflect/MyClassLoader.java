package indi.oracle.java.lang.reflect;

public class MyClassLoader extends ClassLoader {

    public Class<?> loadClass(String name, byte[] bytes) {
        return super.defineClass(name, bytes, 0, bytes.length);
    }
}
