package indi.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import indi.mybatis.entity.MyBatisTestEducationDO;

@Mapper
public interface TestEducationMapper {

    MyBatisTestEducationDO getOne(Long id);
    int insert(MyBatisTestEducationDO entity);
    
    int updateDynamic(MyBatisTestEducationDO entity);
    
    List<MyBatisTestEducationDO> listByUserId(Long id);
}
