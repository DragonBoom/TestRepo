package indi.spring.data.jpa;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = MyBaseRepoImpl.class)// 设置基础Repo，只能用于覆盖默认实现
public class JpaTestConfig {

    // @Bean
    // 意义不明...
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(
                "jdbc:mysql://198.13.46.136:3306/test?serverTimezone=Hongkong&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("!qQ1312449403");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

}
