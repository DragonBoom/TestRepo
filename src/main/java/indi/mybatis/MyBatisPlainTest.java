package indi.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import indi.mybatis.dao.TestEducationMapper;
import indi.mybatis.dao.TestEntityMapper;
import indi.mybatis.entity.MyBatisTestDO;
import indi.mybatis.entity.MyBatisTestEducationDO;
import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * 用于简单的MyBatis的测试；将不使用Spring环境
 * 
 * <p>可用于追查MyBatis的执行流程的源码
 * 
 * @author DragonBoom
 * @since 2022-09-29
 */
@ExtendWith(TestSeparateExtension.class)
class MyBatisPlainTest {

    @Test
    @SneakyThrows
    void selectTest() {
        // 读取配置文件（而不是xml mapper）
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 创建SqlSession（会话工厂）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 建立SqlSession（sql会话）
        try (SqlSession session = sqlSessionFactory.openSession()) {// auto commit = false
            // 方式1：直接用session执行mapper中定义的sql
            // 如果 语句id 有重复，需要指定mapper接口
            Long id = 123L;
            MyBatisTestDO entity = (MyBatisTestDO) session.selectOne("indi.mybatis.dao.TestEntityMapper.getOne", id);
            Assertions.assertNotNull(entity);
            // 测试一级缓存：执行相同的SQL，测试是否会用到缓存（通过日志判断 向数据库请求次数是否小于调用API次数？Y，确实用了缓存（一级缓存，或者说是默认缓存））
            entity = (MyBatisTestDO) session.selectOne("indi.mybatis.dao.TestEntityMapper.getOne", id);
            
            // 方式2：用session创建mapper接口的实例
            TestEntityMapper mapper = session.getMapper(TestEntityMapper.class);
            entity = mapper.getOne(id);
            
            Assertions.assertEquals(entity, entity);
            
            // 测试注解
            // 不需要@Param
            entity = mapper.annotationGetOne(id);
            Assertions.assertNotNull(entity);
        }
        
        // 建立另一个SqlSession（sql会话）
        try (SqlSession session = sqlSessionFactory.openSession()) {
            System.out.println("测试缓存，下面是否会向数据库请求数据？");
            // 👆 若Mapper中有<cache>就不会，否则会
            Long id = 123L;
            MyBatisTestDO entity = (MyBatisTestDO) session.selectOne("indi.mybatis.dao.TestEntityMapper.getOne", id);
            System.out.println("语句执行完毕");
            Assertions.assertNotNull(entity);
            
            // 测试其他命名空间的改会不会刷新二级缓存-第一步
            TestEducationMapper eduMapper = session.getMapper(TestEducationMapper.class);
            MyBatisTestEducationDO eduEntity = eduMapper.getOne(2L);
            eduEntity.setSex("女");
            int result = eduMapper.updateDynamic(eduEntity);
            Assertions.assertEquals(1, result);
            // 改回去
            eduEntity.setSex("男");
            result = eduMapper.updateDynamic(eduEntity);
            Assertions.assertEquals(1, result);
            
            // 测试其他命名空间的改会不会刷新二级缓存-第二步 
            entity = (MyBatisTestDO) session.selectOne("indi.mybatis.dao.TestEntityMapper.getOne", id);
            Assertions.assertNotNull(entity);
        }
    }

}
