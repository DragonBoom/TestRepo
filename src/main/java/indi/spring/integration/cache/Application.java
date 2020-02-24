package indi.spring.integration.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 本类是供测试用例定位自动扫描起始位置，及声明配置性注解的启动类
 * 
 * @author wzh
 * @since 2020.02.17
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching// 也可用于配置类，但还是放在启动类更加合适
public class Application {

    public static void main(String[] args) {
        System.out.println("start at: " + Application.class.getName());// 测试用例不会实际执行该方法，只会识别、获取本类的注解
        SpringApplication.run(Application.class, args);
        
    }
}
