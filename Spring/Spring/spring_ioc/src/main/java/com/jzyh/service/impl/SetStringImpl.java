package com.jzyh.service.impl;

import com.jzyh.service.SetStringDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class SetStringImpl implements SetStringDao {

//    简单类型注入
//    替换资源
    @Value("${name}")
    private String name;

    public void save() {
        System.out.println("String注入:" + name);
    }
}
