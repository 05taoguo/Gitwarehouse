package cn.jzyh;

import cn.jzyh.dao.UserDao;
import cn.jzyh.domain.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Select_Lambda {

    @Autowired
    private UserDao userDao;

    @Test
    void testGetAll() {
        //方式一：按条件查询
/*        QueryWrapper wrapper = new QueryWrapper();
        wrapper.lt("age",71);
        List<User> userList = userDao.selectList(wrapper);
        System.out.println(userList);*/

        //方式二：lambda格式按条件查询
/*        QueryWrapper<User> wrapper = new QueryWrapper();
        //wrapper.lambda().lt(User::getAge,71); 小于：lt 大于：gt
        wrapper.lambda().lt(User::getAge, 670);
        List<User> userList = userDao.selectList(wrapper);
        System.out.println(userList);*/

        //方式三：lambda格式按条件查询
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        //小于：lt 大于：gt   And关系
//        lqw.lt(User::getAge, 500).ge(User::getAge, 50);

        //或者| or 关系
        lqw.lt(User::getAge,120).or().gt(User::getAge,300);
        List<User> userList = userDao.selectList(lqw);
        System.out.println(userList);
    }

}
