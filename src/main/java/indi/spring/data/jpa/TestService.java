/**
 * 
 */
package indi.spring.data.jpa;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import indi.spring.datasource.MultiDataSource;
import indi.spring.datasource.MyConfiguration;

/**
 * @author wzh
 * @since 2020.06.23
 */
@Service
public class TestService {

    @Transactional
    @MultiDataSource(key2 = MyConfiguration.DB_KEY_LINODE)// 切换数据源
    public void go() {
        
        // 执行SQL
        TestEntityDO2 entity = new TestEntityDO2();
        entity.setUsername(String.valueOf(System.currentTimeMillis()));
        entity.setCreateTime(new Date());
        
        dao2.save(entity);
    }
    
    @Autowired
    private TestEntityDao2 dao2;
}
