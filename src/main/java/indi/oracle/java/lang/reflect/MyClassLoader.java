package indi.oracle.java.lang.reflect;

public class MyClassLoader extends ClassLoader {

    /**
     * 自定义的便捷地加载类的方法；作用是将ClassLoader的引入class文件的方法暴露出来
     * 
     * @author DragonBoom
     * @since 2020.06.19
     * @param name
     * @param bytes
     * @return
     */
    public Class<?> loadClass(String name, byte[] bytes) {
        return super.defineClass(name, bytes, 0, bytes.length);
    }

    @Override
    public Package getPackage(String name) {// 注意，这里将继承的protected方法开放为public！
        // TODO Auto-generated method stub
        return super.getPackage(name);
    }
    
    
}
