package indi.spring.data.jpa;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "test_entity2")// 测试发现，该注解的优先级比@Entity更高；绑定的库表为`test_entity2`
@Data
public class TestEntityDO2 {

    @Id
//    @Column(name = "id_")// mysql 中，下划线不会被无视
    @GeneratedValue// 自增
    @Column(name = "id")
    private Long id;

    @Column private String username;// 用户名
    @Column private Date createTime;// 创建时间
}
