package indi.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityDao extends JpaRepository<TestEntityDO, Long> {

}