package com.jzyh.service.impl;

import com.jzyh.service.TestA;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class TestAImpl implements TestA {

    public void save() {
        System.out.println("自动装配");
    }
}
