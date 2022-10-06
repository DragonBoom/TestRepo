package indi.mybatis.data;

import java.util.Date;
import java.util.List;

import indi.mybatis.entity.MyBatisTestEducationDO;
import lombok.Data;

/**
 * 用于绑定Mapper.xml 里的resultMap
 * 
 * @author DragonBoom
 * @since 2022-09-30
 */
@Data
public class MyBatisTestEducationDTO {
    private Long id;
    private String username;// 用户名
    private String sex;// 性别
    private String mobilePhone;// 电话号码
    private Date createTime;// 创建时间
    
    private List<MyBatisTestEducationDO> educations;
}
