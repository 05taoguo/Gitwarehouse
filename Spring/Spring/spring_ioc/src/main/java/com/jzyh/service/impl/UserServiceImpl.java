package com.jzyh.service.impl;

import com.jzyh.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository
@ComponentScan("com.jzyh.service")

//@Scope("singleton")
//@Scope("prototype")


//配置多个
//@ComponentScan({"com.jzyh.service","com.jzyh"})

//@Component("bookDao")
/*
//业务层
@Service

//数据层
@Repository

//表现层
@Controller
*/

public class UserServiceImpl implements UserService {

    public void save() {
        System.out.println("数据");
    }

//    初始化
    @PostConstruct
    public void init() {
        System.out.println("初始化···");
    }

//    非单例不执行
    @PreDestroy
    public void destroy() {
        System.out.println("销毁···");
    }

}
