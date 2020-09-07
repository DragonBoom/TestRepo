/**
 * 
 */
package indi.spring.datasource;

import org.eclipse.jetty.util.log.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.spring.data.jpa.TestService;
import indi.test.TestSeparateExtension;
import lombok.extern.slf4j.Slf4j;

/**
 * 注意，通过配置类的注解扫描了indi.spring.data.jpa包下的配置类与实体类
 * 
 * @author wzh
 * @since 2020.06.23
 */
@SpringBootTest
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
// No active profile set, falling back to default profiles: default
@ActiveProfiles("mysql-remote")// 声明需要【额外】引用的配置文件（当前测试用例为：使用远程MySQL服务器）
@Slf4j
public class MultiDataSourceTest {
    
    @Test
//    @Transactional
//    @Commit
    void go() {
        log.info("---------------------{}");
//        DynamicDataSource.setLookupKey(key);
        // 经测试，对测试方法添加注解，无法被AOP切面拦截
        service.go();
    }
    
    @Autowired
    private TestService service;
}
