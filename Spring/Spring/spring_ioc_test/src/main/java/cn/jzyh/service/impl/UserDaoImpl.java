package cn.jzyh.service.impl;

import cn.jzyh.service.TeacherDao;
import cn.jzyh.service.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoImpl implements UserDao {
    private TeacherDao teacherDao;

//    构造方法
    public UserDaoImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public UserDaoImpl(){

    }

/*    //    引用类型
    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }*/


    @Override
    public void save() {

        teacherDao.save();
        this.save2();
    }

    @Override
    public void save2() {
        System.out.println("111");
    }


}
