package cn.jzyh.dao.impl;

import cn.jzyh.dao.User;

public class UserImpl implements User {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void save() {
        System.out.println("你好"+ name);
    }
}
