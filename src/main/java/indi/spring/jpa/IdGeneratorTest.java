package indi.spring.jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
// No active profile set, falling back to default profiles: default
@ActiveProfiles// 声明需要【额外】引用的配置文件（当前测试用例为：仅使用默认配置文件）
class IdGeneratorTest {

    @Test
    void saveNewEntity() {
        // 测试的自变量见实体类的主键的注解
        TestEntityDO3 newEntity = new TestEntityDO3();
        newEntity.setUsername("testUser");
        testEntityDao3.save(newEntity);
    }
    
    @Autowired
    private TestEntityDao3 testEntityDao3;
}
