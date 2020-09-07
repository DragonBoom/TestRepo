package indi.spring.data.jpa;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

@Entity
@Table(name = "test_entity2")// 测试发现，该注解的优先级比@Entity更高
@Data
public class TestEntityDO3 {

    @Id
//    @Column(name = "id_")// mysql 中，下划线不会被无视
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TestEntityDO3SeqGenerator")// 使用数据库提供的主键作为默认值
    @SequenceGenerator(name = "TestEntityDO3SeqGenerator", sequenceName = "hibernate_sequence")// 指定序列（可指定表...）
//    @GeneratedValue(strategy = GenerationType.TABLE)// 使用专门维护递增值的表（若没有将新增，表名形如hibernate_sequence）的值作为id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq111")// 自增
//    @TableGenerator(name = "TestEntityDO3Generator")
    @Column(name = "id")
    private Long id;

    @Column private String username;// 用户名
    @Column private Date createTime;// 创建时间
}
