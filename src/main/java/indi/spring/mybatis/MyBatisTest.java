/**
 * 
 */
package indi.spring.mybatis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import indi.spring.mybatis.dao.TestEntityDao;
import indi.test.TestSeparateExtension;

/**
 * 需注意启动类的@MapperScan注解
 * 
 * @author wzh
 * @since 2020.07.19
 */
@ExtendWith({TestSeparateExtension.class, SpringExtension.class})
@SpringBootTest
@ActiveProfiles("mybatis")
public class MyBatisTest {
    @Autowired
    private TestEntityDao dao;

    @Test
    void selectByIdTest() {
        // select
        System.out.println(dao.selectById(123L));
    }
}
