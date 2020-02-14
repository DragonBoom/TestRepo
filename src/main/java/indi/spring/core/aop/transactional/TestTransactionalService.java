package indi.spring.core.aop.transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 调用该类以事务都执行方法
 * 
 * @author wzh
 * @since 2020.01.16
 */
@Service
public class TestTransactionalService {

    @Transactional
    void transactionTest() {
        TestEntityDO e = new TestEntityDO();
        // date !
        e.setUsername(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        dao.save(e);
        System.out.println(dao.count());
    }
    
    @Autowired
    private TestEntityDao dao;
}
