package indi.spring.core.mvc.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JpaTest {
    @Autowired
    private TestDao dao;

    @Test
    void go() {
        System.out.println(dao.count());
    }
}
