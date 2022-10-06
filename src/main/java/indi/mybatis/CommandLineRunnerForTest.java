package indi.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import indi.mybatis.dao.TestEducationMapper;

/**
 * 全面的集成Spring环境的测试（以排除 测试用例可能没加载所有bean 的可能性）
 * 
 * @author DragonBoom
 * @since 2022-09-30
 */
@Component
public class CommandLineRunnerForTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        eduDao.listByUserId(123L);
        eduDao.getOne(123L);
    }
    
    @Autowired
    private TestEducationMapper eduDao;
}
