package cn.jzyh.service.impl;


import cn.jzyh.domin.User;
import cn.jzyh.service.TeacherDao;
import cn.jzyh.service.UserDao;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@Data
@ToString
public class TeacherDaoImpl implements TeacherDao {

    //数组
    private String[] array;
    //list集合
    private List<String> stringList;
    //map集合
    private Map<String,User> userMap;
    //set集合
    private Set<String> set;
    //properties属性
    private Properties properties;

    @Override
    public void save2() {
        System.out.println("泛型");

        System.out.println("数据:" + Arrays.toString(array));

        System.out.println("List:" + stringList);

        System.out.println("Map:" + userMap);

        System.out.println("Set:" + set);

        System.out.println("Properties：" + properties);
    }


    @Override
    public void save() {
        System.out.println("Hello Word");
    }


}
