package indi.spring.core.aop.transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import indi.data.Pair;
import indi.util.extension.TestSeparateExtension;

/**
 * 测试@Transactional注解的事务机制
 */
@SpringBootTest
@ExtendWith({SpringExtension.class, TestSeparateExtension.class})
class TransactionalTest {
    
    /**
     * 事务继承测试
     * 
     * @since 2020.01.16
     */
    @Test
    @Transactional
    @Commit// 默认提交事务！
    @Disabled
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
    
    private void saveUserByNow() {
        TestEntityDO e = new TestEntityDO();
        // date !
        e.setUsername(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        dao.save(e);
    }
    
    @Test
//    @Transactional// 注意测试用例默认不提交
    void transactionTemplateTest() {
        // 执行写操作
//        saveUserByNow();
        // 该方法似乎只是函数式地执行事务逻辑而已，事务的提交是在函数外执行
        // 可保存函数参数，之后在函数外获取函数的执行结果（但似乎意义不大。。。）
        Pair<TransactionStatus, Object> pair = Pair.of(null, null);
        
        transactionTemplate.execute(status -> {
            pair.setFirst(status);
            saveUserByNow();
            System.out.println(status);
            System.out.println("is new transaction ? " + status.isNewTransaction());
            System.out.println("is transaction completed ? " + status.isCompleted());// false
            System.out.println("is transaction rollback only ? " + status.isRollbackOnly());
            return null;// 返回null表示没有返回值
        });
        
        System.out.println(pair.getFirst().isCompleted());// true
    }
    
    @Autowired
    private TestEntityDao dao;
    @Autowired
    private TestTransactionalService testTransactionalService;
    @Autowired
    private TransactionTemplate transactionTemplate;

}
