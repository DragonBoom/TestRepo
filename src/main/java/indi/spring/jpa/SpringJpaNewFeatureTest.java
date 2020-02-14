package indi.spring.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 本类专门用于测试Spring Jpa的新特性（通用测试用例，若某部份内容过多，可将其拆分为新的专门的测试用例）
 * 
 * @author wzh
 * @since 2020.02.09
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
// No active profile set, falling back to default profiles: default
@ActiveProfiles// 声明需要【额外】引用的配置文件（当前测试用例为：仅使用默认配置文件）
class SpringJpaNewFeatureTest {

    /**
     * 测试 Spring Data JPA 1.10 新支持的 `Query by Example`（1.11 的新特性写上了这个，不清楚哪个才是最新的。。。）
     * 
     * <p>文档见： https://docs.spring.io/spring-data/jpa/docs/2.0.3.RELEASE/reference/html/#query-by-example
     * 
     * <p>简称 QBE。是Spring Data Jpa提供的一种快速进行简单查询的方法。想法挺美好，但限制仍过多，不够灵活。有意思的是应用到了domain的概念。
     * 思路是将实体作为查询条件，并辅以匹配器(matcher)进行进一步的约束。
     * 
     * <li>不支持形如 `firstname = ?0 or (firstname = ?1 and lastname = ?2)` 的复杂条件。
     * <li>只支持对字符串进模糊匹配，其他数据类型只能精准匹配
     * 
     * <p>底层是通过反射获取domain实例的参数，以此构建查询的对象，再交由hibernate构建最终的查询sql
     * 
     * @throws ParseException 
     */
    @Test
    void exampleQueryTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 1. Create a new instance of the domain object
        TestEntityDO2 probeEntity = new TestEntityDO2();
        // 2. Set the properties to query
//        probeEntity.setUsername("test");
        probeEntity.setCreateTime(sdf.parse("2020-02-09"));// 测试能否以此进行匹配？Y
        
        // 3. Create the Example
        
        // 3.1 使用接口的静态方法
        Example<TestEntityDO2> example = Example.of(probeEntity);// org.springframework.data.domain.Example
        
        // 3.2 使用ExampleMatcher更详细的定义匹配规则
        ExampleMatcher matcher = ExampleMatcher.matching()     
                .withIgnorePaths("username")// ignore the username property path
//                .withIncludeNullValues(); // include null values
                ;
        Example<TestEntityDO2> example2 = Example.of(probeEntity, matcher);
        
        // 执行查询（JpaRepository接口继承了QueryByExampleExecutor，可执行参数为Example的方法）
        List<TestEntityDO2> result = testEntityDao.findAll(example2);
        System.out.println("result size:" + result.size());
        for (TestEntityDO2 TestEntityDO : result) {
            System.out.println(TestEntityDO);
        }
    }
    
    @Test
    @Disabled
    void placehold() {
        
    }
    
    @Autowired
    private TestEntityDao2 testEntityDao;
    
}
