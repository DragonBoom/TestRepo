package indi.spring.core.aop.transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.util.extension.TestSeparateExtension;

/**
 * 测试@Transactional注解的事务机制
 */
@SpringBootTest
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
class TransactionalTest {
    
    @Test
    void go() {
        
    }
    

}
