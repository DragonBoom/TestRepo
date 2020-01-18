package indi.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

@SpringBootTest(properties = "spring.profiles.active=rabbit")// 使用默认配置
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
class SpringTest {
    @Autowired
    private ConfigurableApplicationContext cac;
    
    @Test
    void go() {
        System.out.println(cac);
        // 启动的是哪个启动类
    }

}
