package indi.spring.core.aop.transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityDao extends JpaRepository<TestEntityDO, Long> {

}
