package indi.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.Data;

/**
 * test_entity = 人，test_education = 学历
 * 
 * <p>该表的每条数据代表一个人的一次学习经历
 * 
 * @author DragonBoom
 * @since 2022-09-30
 */
//@Alias("EducationDO")// 需要在配置文件中用type-aliases-package开启对所在包的扫描 才能生效
@Entity
@Table(name = "test_education")// 测试发现，该注解的优先级比@Entity更高；绑定的库表为`test_entity2`
@Data
public class MyBatisTestEducationDO implements Serializable {
    private static final long serialVersionUID = -4119459799648173243L;
    
    @GeneratedValue
    @Id
    @Column
    private Long id;
    private Long userId;// 关联字段，test_entity.id
    private String username;// 冗余字段
    private String sex;// 冗余字段
    private String schoolName;
    private String major;// 主修
    private Date beginTime;
    private Date endTime;
}
