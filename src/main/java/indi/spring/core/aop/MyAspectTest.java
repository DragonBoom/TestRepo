package indi.spring.core.aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest("indi.spring.core.aop")
@ExtendWith(SpringExtension.class)
public class MyAspectTest {

    @Test
    void go() {
        System.out.println("go");
    }
}
