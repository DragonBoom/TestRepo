package indi.spring.boot.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

/**
 * 似乎启动测试类时，会找到启动类并解析其注解，但不会执行启动类？
 * 
 * 不会执行启动类中main函数的代码，但会根据启动类的位置加载spring bean
 * 
 * @author wzh
 * @since 2020.01.04
 */
@SpringBootTest(properties = "spring.profiles.active=rabbit")// 使用默认配置
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
class SpringTest {
    @Autowired
    private ConfigurableApplicationContext cac;// SpringApplication.run(Application.class, args) 的返回值
    
    @Test
    void go() {
        System.out.println(cac);
        System.out.println(cac.getApplicationName());
        System.out.println(cac.getId());
        // 启动的是哪个启动类？
    }

}
