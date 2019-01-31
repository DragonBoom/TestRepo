package indi.spring.jpa;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TotalTest {
    @Autowired
    private TestEntityDao dao;
    // @Autowired
    private EntityManager entityManager;
    @Autowired
    private EntityManagerFactory factory;
    
    @BeforeAll
    static void before() {
        
    }

    @Test
    // @Transactional
    void startTest() {
        entityManager = factory.createEntityManager();
        System.out.println(entityManager.getClass());
        TestEntityDO entity = new TestEntityDO();
        entity.setId(124L);
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
}
