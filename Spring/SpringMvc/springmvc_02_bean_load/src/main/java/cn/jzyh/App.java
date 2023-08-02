package cn.jzyh;

import cn.jzyh.config.SpringConfig;
import cn.jzyh.controller.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController bean = cxt.getBean(UserController.class);
        System.out.println(bean);
    }
}
