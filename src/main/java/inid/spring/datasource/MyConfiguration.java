package inid.spring.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories
public class MyConfiguration {

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test?useOldAliasMetadataBehavior=true&serverTimezone=UTC&useSSL=false");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // name!!
        dataSource.setPassword("!qQ1312449403");
        return dataSource;
    }
    
    @Bean
    public DataSource dataSource2() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/activiti?useOldAliasMetadataBehavior=true&serverTimezone=UTC&useSSL=false");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver"); // name!!
        dataSource.setPassword("!qQ1312449403");
        return dataSource;
    }

}
