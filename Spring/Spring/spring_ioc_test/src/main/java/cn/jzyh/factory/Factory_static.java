package cn.jzyh.factory;

import cn.jzyh.service.Student1Dao;
import cn.jzyh.service.impl.Student1DaoImpl;

public class Factory_static {

    //    静态工厂
    public static Student1Dao getStudent1Dao(){
        return new Student1DaoImpl();
    }
}
