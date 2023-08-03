package cn.jzyh;

import cn.jzyh.dao.UserDao;
import cn.jzyh.domain.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests2 {

    @Autowired
    private UserDao userDao;

    @Test
    void testGetAll() {
        //方式一：按条件查询
/*        QueryWrapper wrapper = new QueryWrapper();
        wrapper.lt("age",71);
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);*/

        //方式二：lambda格式按条件查询
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.lambda().lt(User::getAge,71);
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }

}
