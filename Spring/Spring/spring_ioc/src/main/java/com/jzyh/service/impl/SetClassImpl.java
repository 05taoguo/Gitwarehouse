package com.jzyh.service.impl;

import com.jzyh.service.BookDao;
import com.jzyh.service.TestA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SetClassImpl implements BookDao {

    //自动装配
    @Autowired
//    @Qualifier("testA")
    private TestA testA;

    public void setTestA(TestA testA) {
        this.testA = testA;
    }

    public void save() {
        System.out.println("自动装配");
        testA.save();
    }

}
