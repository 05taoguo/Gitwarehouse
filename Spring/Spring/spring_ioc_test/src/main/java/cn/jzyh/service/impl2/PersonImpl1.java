package cn.jzyh.service.impl2;

import cn.jzyh.service.PersonDao;
import cn.jzyh.service.PersonDao2;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonImpl1 implements PersonDao {

    private PersonDao2 personDao2;

    public void setPersonImpl1(PersonDao2 personDao2) {
        this.personDao2 = personDao2;
    }


    @Override
    public void save() {
        System.out.println("第一文");
        personDao2.save();
    }
}
