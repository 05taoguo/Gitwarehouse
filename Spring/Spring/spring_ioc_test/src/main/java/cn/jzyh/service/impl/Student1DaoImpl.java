package cn.jzyh.service.impl;

import cn.jzyh.service.Student1Dao;


/*实现bean 的实例化（FactoryBean方式示例化）*/
public class Student1DaoImpl implements Student1Dao {

//        构造方法
    public Student1DaoImpl() {
        System.out.println("无参构造···");
    }

    @Override
    public void save() {
        System.out.println("无参调用···");
    }


    //    静态工厂
    @Override
    public void save2() {
        System.out.println("静态工厂···");
    }

    //    实例工厂
    @Override
    public void save3() {
        System.out.println("实例工厂···");
    }

    //FactoryBean
    @Override
    public void save4() {
        System.out.println("FactoryBean---");
    }
}
