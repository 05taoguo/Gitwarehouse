package cn.jzyh.service.impl;

import cn.jzyh.service.StudentDao;

/*实现bean 的生命周期控制*/

public class StudentDaoImpl implements StudentDao {

    public void init(){
        System.out.println("初始化方法···");
    }

    @Override
    public void save() {
        System.out.println("代码块。。。。");
    }

    public void destroy(){
        System.out.println("销毁方法···");
    }


}
