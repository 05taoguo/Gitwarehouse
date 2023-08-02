package cn.jzyh.factory;

import cn.jzyh.service.Student1Dao;
import cn.jzyh.service.impl.Student1DaoImpl;

public class Factory_Instance {

//    实例工厂
    public Student1Dao getStudent1Dao(){
        return new Student1DaoImpl();
    }
}
