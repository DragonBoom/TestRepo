package indi.mybatis.dao;

import org.apache.ibatis.builder.annotation.ProviderContext;

/**
 * 用于为注解动态地提供sql语句
 * 
 * @author DragonBoom
 * @since 2022-10-01
 */
public class TestSqlProvider {

    /**
     * 
     * @param id
     * @param ctx 用于提供 调用者的信息
     * @return
     * @since 2022-10-01
     */
    public String getOne(Long id, ProviderContext ctx) {// 似乎还有其他参数？
        System.out.println(ctx);
        return "select * from test_entity where id = " + id;
    }
}
