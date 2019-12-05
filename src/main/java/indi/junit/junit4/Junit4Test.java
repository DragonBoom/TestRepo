package indi.junit.junit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//@RunWith(SpringJUnit4ClassRunner.class)// 运行Spring的Runner
//@ContextConfiguration(locations = "classpath:applicationContext.xml")// 指定配置文件

//@RunWith(Suite.class)// Suit Runner，用于批量执行junit4测试类
//@SuiteClasses(value = { Junit4SuitRunnerTest.class })// 指定启动的类 
public class Junit4Test {

    @Test
    public void timeoutFileSynTest() throws Exception {
    }
    
}
