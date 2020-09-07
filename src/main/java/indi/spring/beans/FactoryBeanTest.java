package indi.spring.beans;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

@SpringBootTest()
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
public class FactoryBeanTest {
    @Autowired(required = false)
    private MyClass mc;
    @Autowired(required = false)
    private MyClass mc2;
    @Autowired(required = false)
    private MyFactoryBean mfb;

    /**
     * FactoryBean以多例的模式创建bean：根据日志，本类创建了2次实例，每次注入时都会创建一个新的实例
     */
    @Test
    void injectTest() throws Exception {
        System.out.println(mc);
        System.out.println(mc);// not create new instance
        System.out.println(mc2);
        System.out.println(mc2);
        
        System.out.println(mfb);// not null
    }
}
