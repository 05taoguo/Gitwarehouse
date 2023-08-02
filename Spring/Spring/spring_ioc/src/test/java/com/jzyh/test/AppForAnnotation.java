package com.jzyh.test;

import com.jzyh.config.SpringConfig;
import com.jzyh.config.JdbcConfig;
import com.jzyh.service.BookDao;
import com.jzyh.service.SetStringDao;
import com.jzyh.service.UserService;
import com.jzyh.service.impl.SetClassImpl;
import com.jzyh.service.impl.SetStringImpl;
import com.jzyh.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class AppForAnnotation {

    @Test
    public void test1() {
        //加载配置类
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService bookDao1 = app.getBean(UserServiceImpl.class);
        UserService bookDao2 = app.getBean(UserServiceImpl.class);

        System.out.println(bookDao1);
        System.out.println(bookDao2);

        bookDao1.save();
        bookDao2.save();

        app.close();

    }



    @Test
    public void test3() {
        //加载配置类
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);

        SetStringDao bookDao = app.getBean(SetStringImpl.class);

        bookDao.save();
    }

    @Test
    public void test4() {
        //加载配置类
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);

        DataSource dataSource = app.getBean(DataSource.class);
        System.out.println(dataSource);

    }
}
