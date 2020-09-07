package indi.spring.mybatis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import indi.spring.mybatis.entity.TestEntityDO;

public interface TestEntityDao {

    TestEntityDO selectById(Long id);
}
