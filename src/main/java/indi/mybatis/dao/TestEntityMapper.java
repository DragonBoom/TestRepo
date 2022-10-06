package indi.mybatis.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import indi.mybatis.data.MyBatisTestEducationDTO;
import indi.mybatis.entity.MyBatisTestDO;

@Mapper
public interface TestEntityMapper {

    /*
     * 下面是 CURD
     */
    MyBatisTestDO getOne(Long id);
    int delete(Long id);
    /*
     * 似乎不好实现save。要把 新增 和 更新 区分开来。如果有需要，在service层做整合比较好
     * 
     * @since 2022-09-28
     */
    int insert(MyBatisTestDO entity);
    int update(MyBatisTestDO entity);
    
    
    /*
     * 下面是 列表查询
     */
    List<MyBatisTestDO> listAll();
    /** 无参全表查询。返回Map的集合而不是DO的集合，是为了便于按业务需要去扩展字段 */
    List<Map<String, Object>> queryAll();
    List<Map<String, Object>> queryAllPage(Map<String, Object> params);
    /** 分页查询。返回Map的集合而不是DO的集合，是为了便于按业务需要去扩展字段 */
    List<Map<String, Object>> queryPage(Map<String, Object> params, int offset, int size);
    
    /**
     * 获取用户的所有教育经历信息。将把关联查询的结果集直接映射到DTO中
     * 
     * <p>为集合嵌套select的版本（不推荐）
     * 
     * @param id
     * @return
     * @since 2022-09-30
     */
    MyBatisTestEducationDTO getFullEducation(Long id);
    /**
     * 获取用户的所有教育经历信息。将把关联查询的结果集直接映射到DTO中
     * 
     * <p>为集合嵌套结果映射的版本
     * 
     * @param id
     * @return
     * @since 2022-09-30
     */
    MyBatisTestEducationDTO getFullEducation2(Long id);
    
    
    /*
     * 下面是 对一些机制的测试
     */
    Date getCreateTime(Long id);
    List<Map<String, Object>> fuzzQueryUsername(String username);
    List<Map<String, Object>> queryPage1(int offset, int size);
    List<Map<String, Object>> queryPage2(int offset, int size);
    int insertWithNull(Map<String, Object> param);
    
    
    /*
     * 下面是 用注解实现的方法。命名为 annotationXXX
     */
    @Select("select * from test_entity where id = #{id}")// 不需要用@Param声明如何传参
    MyBatisTestDO annotationGetOne(Long id);
    @SelectProvider(type = TestSqlProvider.class, method = "getOne")
    MyBatisTestDO annotationGetOne2(Long id);
    @Insert("INSERT INTO test_entity (username, sex, mobile_phone, create_time) "
            + "VALUES(#{username}, #{sex}, #{mobilePhone}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int annotationInsert(MyBatisTestDO entity);
    /**
     * 使用动态SQL，只更新DO中不为空的字段
     * 
     * @param entity
     * @return
     * @since 2022-10-02
     */
    int updateNotNull(MyBatisTestDO entity);
    /** 测试动态标签<foreach> */
    List<MyBatisTestDO> queryByIds(List<Long> ids);
    
}
