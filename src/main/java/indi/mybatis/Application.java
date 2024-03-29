package indi.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 指定Mapper的扫描路径（可通过配置项mybatis.mapper-locations实现）
// @MapperScan("indi.mybatis.dao")
public class Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "mybatis");
        
        System.out.println("start at: " + Application.class.getName());// useless...
        SpringApplication.run(Application.class, args);
        
    }
}
