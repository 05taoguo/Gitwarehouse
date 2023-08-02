package cn.jzyh;

import cn.jzyh.config.SpringConfig;
import cn.jzyh.controller.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class app {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext act = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = act.getBean(UserController.class);
        System.out.println(userController.save());

    }
}
