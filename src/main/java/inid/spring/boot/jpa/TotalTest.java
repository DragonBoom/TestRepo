package inid.spring.boot.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TotalTest {
    @Autowired
    TestEntityDao dao;

    @Test
    void startTest() {
        TestEntityDO entity = new TestEntityDO();
        entity.setId(123L);
        entity.setUsername("wahaha");
        if (!dao.existsById(entity.getId())) {
            dao.save(entity);
        } else {
            System.out.println(dao.getOne(entity.getId()));
        }
    }
}
