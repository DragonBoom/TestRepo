package indi.spring.beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<MyClass> {

    @Override
    public MyClass getObject() throws Exception {
        System.out.println("FactoryBean creating object");
        MyClass myClass = new MyClass();
        myClass.setName("yahello");
        return myClass;
    }

    @Override
    public Class<MyClass> getObjectType() {
        return MyClass.class;
    }
    
    @Override
    public boolean isSingleton() {
        return false;
    }

}
