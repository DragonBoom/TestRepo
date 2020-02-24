package indi.spring.data.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// No active profile set, falling back to default profiles: default
@ActiveProfiles// 声明需要【额外】引用的配置文件（当前测试用例为：仅使用默认配置文件）
class EntityManagerTest {
    
    @BeforeAll
    static void before() {
        
    }

    @Test
    // @Transactional
    void entityManagerTest() {
        // 利用工厂模式创建EntityManager
        entityManager = factory.createEntityManager();
        System.out.println(entityManager.getClass());
        TestEntityDO2 entity = new TestEntityDO2();
        entity.setId(124L);
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    
    @Autowired
    private TestEntityDao2 dao;
    // @Autowired
    private EntityManager entityManager;
    @Autowired
    private EntityManagerFactory factory;
}
