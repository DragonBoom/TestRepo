package indi.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityDao2 extends JpaRepository<TestEntityDO2, Long>, IndividualQueryDao {

}
