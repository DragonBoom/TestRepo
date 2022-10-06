package indi.spring.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 *  由于启动SpirngBootApplication类会加载其所在包下的所有bean，所以测试时必须为每类测试设置一个专用的启动类。。。
 * 
 * @author wzh
 * @since 2020.01.04
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("start at: " + Application.class.getName());// well...
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        ctx.setId("test");
        
    }
}
