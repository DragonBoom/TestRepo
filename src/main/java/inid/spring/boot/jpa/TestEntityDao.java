package inid.spring.boot.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import indi.spring.boot.jpa.TestEntityDO;

public interface TestEntityDao extends JpaRepository<TestEntityDO, Long> {

    public TestEntityDO getByUsername(String username);
}
