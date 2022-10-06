package indi.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * <b>专用</b>于MyBatis测试的实体类
 * 
 * @author DragonBoom
 * @since 2022-09-28
 */
@Entity
@Table(name = "test_entity")// 测试发现，该注解的优先级比@Entity更高；绑定的库表为`test_entity2`
@Data
public class MyBatisTestDO implements Serializable {
    private static final long serialVersionUID = 5137756441180642999L;
    
    //    @Column(name = "id_")// mysql 中，下划线不会被无视
//    @GeneratedValue// 自增
    @GeneratedValue
    @Id// 必须
    @Column// 必须
    private Long id;
    private String username;// 用户名
    private String sex;// 性别
    private String mobilePhone;// 电话号码
    private Date createTime;// 创建时间
    
}
