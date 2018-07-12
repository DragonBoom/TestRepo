package indi.dbcp2;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/configuration.xml");
        
        DataSource d = (DataSource) context.getBean("dataSource");
        System.out.println(d.getConnection().isClosed());
        context.close();
    }
}
