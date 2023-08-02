package com.jzyh.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    //    初始化学习
    @Test
    public void test1() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml.bak");

/*        有id
        UserService bookDao = (UserService) app.getBean("bookDao");
        System.out.println(bookDao);*/

/*        无id，类
        UserServiceImpl bean = app.getBean(UserServiceImpl.class);
        System.out.println(bean);*/


    }

}
