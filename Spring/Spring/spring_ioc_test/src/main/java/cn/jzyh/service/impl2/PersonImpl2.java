package cn.jzyh.service.impl2;

import cn.jzyh.service.PersonDao2;

public class PersonImpl2 implements PersonDao2 {

    @Override
    public void save() {
        System.out.println("第二文");
    }
}
