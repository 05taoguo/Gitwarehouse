package cn.jzyh.test;

import cn.jzyh.dao.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

public class DataSourceTest {

//    测试c3p0数据源
    @Test
    public void test1() throws Exception {

//        文件路径加载配置文件
        FileSystemXmlApplicationContext app1 = new FileSystemXmlApplicationContext("E:\\Git备用仓库\\Spring\\spring_ioc_anno\\src\\test\\resources\\applicationContext.xml");

//        名称获取
        DataSource dataSource1 = (DataSource) app1.getBean("c3p0");

        System.out.println(dataSource1);
    }

    @Test
    public void test2(){
//        类加载配置文件
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

//        名称+指定类型
        User user = app.getBean("user",User.class);

        user.save();
    }

    @Test
    public void test3(){
//        加载多个配置文件
//        ApplicationContext app = new ClassPathXmlApplicationContext("brand1","brand2");
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

//        通过类型获取
        User user = app.getBean(User.class);

        user.save();
    }
}
