/**
 * 
 */
package indi.mybatis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;

import indi.bean.BeanUtils;
import indi.mybatis.dao.TestEducationMapper;
import indi.mybatis.dao.TestEntityMapper;
import indi.mybatis.data.MyBatisTestEducationDTO;
import indi.mybatis.entity.MyBatisTestDO;
import indi.mybatis.entity.MyBatisTestEducationDO;
import indi.test.TestSeparateExtension;
import lombok.SneakyThrows;

/**
 * 需注意启动类的@MapperScan注解
 * 
 * @author wzh
 * @since 2020.07.19
 */
@ActiveProfiles({"secret", "mybatis"})// 加载测试用配置文件
@ExtendWith({TestSeparateExtension.class, SpringExtension.class})
@SpringBootTest// 为测试分页插件，不能用下面的注解
//@MybatisTest// 专门测试MyBatis（不会扫描其他bean），可结合其他测试注解使用
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)// 禁止使用测试数据库；若不加上，将自动使用H2数据库
class MyBatisTest {
    
    /**
     * 测试最基本的 增删改查
     * 
     * @since 2022-09-28
     */
    @Test
    @Transactional
    @Commit
//    @Rollback
//    @Disabled
    void curdTest() {
        MyBatisTestDO entity = buildRandomEntity();
        
        int result = 0;
        result = entityMapper.annotationInsert(entity);// 增
        System.out.println(entity);
        Assertions.assertEquals(1, result);
        Assertions.assertNotNull(entity.getId());// 插入后，会回过头来为实体类设置id
        
        entity.setSex("女");
//        result = entityMapper.update(entity);// 改
        result = entityMapper.updateNotNull(entity);// 改（使用动态SQL的<set>+<where>的版本）
        Assertions.assertEquals(1, result);
        
        entity = entityMapper.getOne(entity.getId());// 查
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("女", entity.getSex());
        
//        result = dao.delete(entity.getId());// 删
//        Assertions.assertEquals(1, result);
    }
    
    private MyBatisTestDO buildRandomEntity() {
        MyBatisTestDO entity = new MyBatisTestDO();
        entity.setUsername("自动人" + genRandom(3));
        entity.setSex("男");
        entity.setMobilePhone("130" + genRandom(8));
        entity.setCreateTime(new Date());
        return entity;
    }
    
    /**
     * 测试一级缓存
     * 
     * @throws ParseException
     * @since 2022-10-02
     */
    @Test
    @Transactional
    @Commit
//    @Rollback
//    @Disabled
    void cacheTest() throws ParseException {
        System.out.println(sqlSession);// 如：org.mybatis.spring.SqlSessionTemplate@1b9776f5
        Assertions.assertNotNull(sqlSession);
        
        Long userId = 123L;
        // 测试缓存-第一步（通过日志可看到，下面将只向数据库请求一次。因为用到了一级缓存）
        System.out.println("select start");
        MyBatisTestDO entity = entityMapper.getOne(userId);// 查
        entity = entityMapper.getOne(userId);// 查
        entity = entityMapper.getOne(userId);// 查
        System.out.println("select end");
        
        MyBatisTestEducationDO eduEntity = genRandomEdu(entity);
        int result = eduMapper.insert(eduEntity);
        Assertions.assertEquals(1, result);
        
        // 测试缓存-第二步（通过日志可看到，下面将向数据库请求一次。因为之前执行过 增/删/改，把缓存刷新了）
        // 哪怕不是同一张表的增删改也会刷新缓存
        System.out.println("下面将执行查询");
        entity = entityMapper.getOne(userId);// 查 这里向数据库请求
        System.out.println("下面将执行查询");
        entity = entityMapper.getOne(userId);// 查 这里使用了缓存
        
        // 测试主动清楚一级缓存
        sqlSession.clearCache();
        System.out.println("下面将执行查询");
        entity = entityMapper.getOne(userId);// 查 这里向数据库请求
    }
    
    /**
     * 获得指定长度的随机数字符串
     * 
     * @param len 得到的整形的长度
     * @return
     * @since 2022-09-28
     */
    private String genRandom(int len) {
        return Long.toString(Math.abs(new Random().nextLong())).substring(0, len);
    }
    
    /**
     * 测试列表查询，最终测试分页查询
     * 
     * @since 2022-09-29
     */
    /**
     * 
     * @since 2022-09-29
     */
    @Test
    @Transactional
    @Rollback
//    @Disabled
    void listTest() {
        // 1. resultType=do
        PageHelper.startPage(1, 10);// 测试分页插件；页码居然是从1而不是0开始。。。
        List<MyBatisTestDO> result = entityMapper.listAll();
        System.out.println(result);
        Assertions.assertTrue(result.size() > 0);
        // 2. resultType=map
        List<Map<String, Object>> result2 = entityMapper.queryAll();
        System.out.println(result2);
        Assertions.assertTrue(result2.size() > 0);
        
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", 123L);
        // 3. 使用map作为参数，且用了动态SQL的if元素
        List<Map<String, Object>> result3 = entityMapper.queryAllPage(params);
        System.out.println(result3);
        Assertions.assertTrue(result3.size() > 0);
        // 4. 使用map作为参数，用了动态SQL，将传入不定的参数
        // 当有多个参数时，没法直接使用map里的键。报错：Available parameters are [offset, size, params, param3, param1, param2]
        // 但可用params.id这样的方式
        params.clear();
        params.put("sex", "女");
        params.put("username", "动");
        List<Map<String, Object>> result4 = entityMapper.queryPage(params, 0, 2);
        System.out.println(result4);
        Assertions.assertTrue(result4.size() > 0);
    }

    /**
     * 测试多表操作
     * 
     * @since 2022-09-30
     */
    @Test
    @Transactional
    @SneakyThrows
    @Commit
//    @Rollback
    @Disabled
    void multiTableTest() {
        // 向用于关联的学历表插入新生成的随机数据
        MyBatisTestDO entity = entityMapper.getOne(142L);
        MyBatisTestEducationDO educationEntity = genRandomEdu(entity);

        int result = eduMapper.insert(educationEntity);
        Assertions.assertEquals(1, result);

        // 测试mapper的集合嵌套select查询
        MyBatisTestEducationDTO dto = entityMapper.getFullEducation(123L);
        System.out.println(dto);
        Assertions.assertTrue(dto.getEducations() != null && dto.getEducations().size() > 0);
        // 测试mapper的集合嵌套结果映射
        dto = entityMapper.getFullEducation2(123L);
        System.out.println(dto);
    }
    
    MyBatisTestEducationDO genRandomEdu(MyBatisTestDO entity) throws ParseException {
        MyBatisTestEducationDO eduEntity = new MyBatisTestEducationDO();
        BeanUtils.copySelectedProperties(entity, eduEntity).copy("id", "userId").copy("username").copy("sex");
        eduEntity.setSchoolName("自动化学校" + genRandom(3));
        eduEntity.setMajor("自动化" + genRandom(1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 虽然是随机的，但也要确保endTime>beginTime
        int random = Integer.parseInt(genRandom(1));
        eduEntity.setBeginTime(sdf.parse("201" + random + "-09-01"));
        eduEntity.setEndTime(sdf.parse("201" + (random + 4 < 10 ? random + 4 : 9) + "-10-09"));
        
        return eduEntity;
    }

    /**
     * 对MyBatis的一些特性的测试
     * 
     * @since 2022-09-29
     */
    @Test
    @Transactional
    @Commit
//    @Rollback
    @Disabled
    void featureTest() {
        // 测试，当resultType为空时，能不能自动匹配数据格式？N，必须有`resultType`或`resultMap`属性
        // resultType 支持 date
        Date date = entityMapper.getCreateTime(123L);
        Assertions.assertEquals(java.util.Date.class, date.getClass());//  
        Assertions.assertNotNull(date);
        
        // 测试sql中的like，及如何拼接通配符
        List<Map<String, Object>> result = entityMapper.fuzzQueryUsername("动");
        System.out.println(result);
        Assertions.assertTrue(result.size() > 0);
        // 测试多参数1，写法1：直接用方法的参数名
        List<Map<String, Object>> result2 = entityMapper.queryPage1(1, 1);
        System.out.println(result2);
        Assertions.assertTrue(result2.size() > 0);
        // 测试多参数1，写法2：用param1、param2（而不是网上流传的arg0、arg1）
        List<Map<String, Object>> result3 = entityMapper.queryPage2(1, 1);
        System.out.println(result3);
        Assertions.assertTrue(result3.size() > 0);
        // 测试插入null值时是否需要指定jdbcType
        MyBatisTestDO entity = buildRandomEntity();
        entity.setMobilePhone(null);
        entity.setSex(null);
        int result4 = entityMapper.insert(entity);// 增
        System.out.println(entity);
        Assertions.assertEquals(1, result4);
        Assertions.assertNotNull(entity.getId());// 插入后，会回过头来为实体类设置id
        
        // 测试使用注解而不是xml
        entity = entityMapper.annotationGetOne(entity.getId());
        // 测试使用注解中的@SelectProvider
        entity = entityMapper.annotationGetOne2(entity.getId());
        Assertions.assertNotNull(entity);
        // 测试动态注解<foreach>
        List<MyBatisTestDO> result5 = entityMapper.queryByIds(Lists.newArrayList(123L, 142L));
        System.out.println(result5);
        Assertions.assertTrue(result5.size() > 0);
    }
    
    @Autowired
    private TestEntityMapper entityMapper;
    @Autowired
    private TestEducationMapper eduMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private SqlSession sqlSession;
}
