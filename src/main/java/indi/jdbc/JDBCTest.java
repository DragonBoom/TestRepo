package indi.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 
 * 
 * @author wzh
 * @since 2020.07.01
 */
public class JDBCTest {
    private static final String 
    JDBC_URL = 
    "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=Hongkong&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
    JDBC_USER_NAME = "root", 
    JDBC_PASSWORD = "!qQ1312449403";

    /**
     * 测试使用JDBC开启事务访问数据库
     * 
     * <p>可以不记得具体参数/方法，但必须记住流程
     * 
     * @author DragonBoom
     * @since 2020.07.01
     */
    @Test
    @Disabled
    void jdbcDirectlyTest() throws ClassNotFoundException, SQLException {
        // 加载MySQL数据库驱动
        // 是否不必再主动加载驱动？Y
//        Class.forName("com.mysql.jdbc.Driver");// 旧
//        Class.forName("com.mysql.cj.jdbc.Driver");// 新，cj指Connector/J？
        
        // 通过DriverManager获取连接
        System.out.println(DriverManager.getDrivers());
        Enumeration<Driver> drivers = DriverManager.getDrivers();// java.util.Vector
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println(driver);
        }
        
        // 通过DriverManager，打印已加载的数据库驱动
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER_NAME, JDBC_PASSWORD);
        
        System.out.println("is auto commit ? " + connection.getAutoCommit());
        
        // 1. 执行查询
        // 使用Statement查询
        String username = queryUsernameByStatement(123, connection);
        System.out.println("username1: " + username);
        // 使用PreparedStatement查询
        String username2 = queryUsernameByPreparedStatement(123, connection);
        System.out.println("username2: " + username2);
        
        // ！！事务地插入一条数据
        int effectedCount = insertWithTransaction(new Date(), "test20200701", connection);
        System.out.println(effectedCount);
        
        // 关闭连接
        connection.close();
    }
    
    
    /** 利用Statement执行查询 */
    private String queryUsernameByStatement(long id, Connection connection) throws SQLException {
        // 获取SQL语句对象，注意看该方法的注释
        // Statement只能执行一次
        Statement statement = connection.createStatement();
        // 执行查询（直接执行）
        // 不可设置参数
        ResultSet rs = statement.executeQuery("select t.* from test_entity t where t.id = " + id);
        
        // 操作结果集，获取查询结果
        // 需要逐行处理
//        boolean first = rs.first();
//        System.out.println(first);
        boolean next = rs.next();
        System.out.println("move next row success ?" + next);

        return rs.getString("username");
    }
    
    /**利用PreparedStatement执行查询*/
    private String queryUsernameByPreparedStatement(long id, Connection connection) throws SQLException {
        // 获取SQL语句对象，注意看该方法的注释
        // PreparedStatement可执行多次
        PreparedStatement statement = connection.prepareStatement("select t.* from test_entity t where t.id = ? ");// 声明自增字段
        // 可设置参数
        statement.setLong(1, id);
        
        // 执行查询（先创建对象再执行）
        System.out.println("执行PreparedStatement成功？" + statement.execute());
        ResultSet rs = statement.getResultSet();
        // 操作结果集，获取查询结果
        // 需要逐行处理
//        boolean first = rs.first();
//        System.out.println(first);
        boolean next = rs.next();
        System.out.println("move next row success ?" + next);
        
        return rs.getString("username");
    }
    
    /** 带事务地插入数据 */
    private int insertWithTransaction(Date createTime, String username, Connection connection) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        // 关闭自动提交，从而开启事务
        if (autoCommit) {
            connection.setAutoCommit(false);
        }
        // 获取SQL语句对象
        PreparedStatement prepareStatement = 
                connection.prepareStatement("insert into test_entity (create_time, username) values(?, ?) ");
        // 设置参数
        prepareStatement.setTimestamp(1, new Timestamp(createTime.getTime()));// timestamp对应MySQL的datetime
        prepareStatement.setString(2, username);
        // 执行查询
        // true表示有结果集，false表示数据库返回了更新数量或没有返回
        boolean executeResult = prepareStatement.execute();
        // 提交事务
        connection.commit();
        // 获取更新数量或结果集
        int updateCount = -1;
        if (executeResult) {
            throw new RuntimeException("insert 操作不应该返回结果集！！");
        } else {
            updateCount = prepareStatement.getUpdateCount();
            System.out.println("更新数量：" + updateCount);
        }
        
        // 还原自动提交
        if (autoCommit) {
            connection.setAutoCommit(true);
        }
        return updateCount;
    }
    
    /**
     * 通过连接池获取链接 
     * 
     * @throws SQLException 
     */
    @Test
    void dateSourcePoolTest() throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(JDBC_URL);
        hikariDataSource.setUsername(JDBC_USER_NAME);
        hikariDataSource.setPassword(JDBC_PASSWORD);

        Connection connection = hikariDataSource.getConnection();
        
        String username = queryUsernameByPreparedStatement(1, connection);
        System.out.println(username);// print: 1592911763146
        hikariDataSource.close();
    } 
    
}
