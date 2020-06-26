package indi.spring.datasource;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories
@ComponentScan("indi.spring.data.jpa")// 只使用ComponentScan，会报无法识别实体类的异常，需要配合下面的注解使用
@EntityScan("indi.spring.data.jpa")
public class MyConfiguration {

    /**
     * 自定义数据源
     * @author DragonBoom
     * @since 2020.06.23
     * @return
     */
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setUsername("root");
//        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useOldAliasMetadataBehavior=true&serverTimezone=UTC&useSSL=false");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // name!!
//        dataSource.setPassword("!qQ1312449403");
//        return dataSource;
//    }
    
    public static final String DB_KEY_LOCAL = "local";
    
    public static final String DB_KEY_LINODE = "linode1";

    /**
     * 自定义动态数据远
     * 
     * @author DragonBoom
     * @since 2020.06.23
     * @return
     */
    @Bean
    public DataSource dataSource2() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("!qQ1312449403");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useOldAliasMetadataBehavior=true&serverTimezone=UTC&useSSL=false");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");// 是DriverClassName 而不是 DriverClass!!
        
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setUsername("root");
        dataSource2.setPassword("!qQ1312449403");
        dataSource2.setJdbcUrl("jdbc:mysql://172.104.66.71:3306/test?serverTimezone=Hongkong&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");// 是DriverClassName 而不是 DriverClass!!

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DB_KEY_LOCAL, dataSource);
        targetDataSources.put(DB_KEY_LINODE, dataSource2);
        
        
        dynamicDataSource.setTargetDataSources(targetDataSources);
        // 设置默认数据库
        // 暂不清楚有何深入影响？但肯定的是，在启动时也会查询数据库
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        
        return dynamicDataSource;
    }

}
