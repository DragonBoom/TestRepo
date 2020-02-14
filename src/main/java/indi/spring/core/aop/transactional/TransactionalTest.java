package indi.spring.core.aop.transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import indi.util.extension.TestSeparateExtension;

/**
 * 测试@Transactional注解的事务机制
 */
@SpringBootTest
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
@Commit// 默认提交事务！
class TransactionalTest {
    
    /**
     * 事务继承测试
     * 
     * @since 2020.01.16
     */
    @Test
    @Transactional
    void transactionalInheritTest() {
        TestEntityDO e = new TestEntityDO();
        // date !
        e.setUsername(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        dao.save(e);
        
        for (int i = 0; i < 10; i++) {
            // 测试打印的实体数量是否正确？Y
            // 即每个子事务能共享其他子事务的影响
            testTransactionalService.transactionTest();
        }
    }
    
    @Autowired
    private TestEntityDao dao;
    @Autowired
    private TestTransactionalService testTransactionalService;

}
