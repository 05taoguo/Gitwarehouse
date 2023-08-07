package cn.jzyh;

import cn.jzyh.dao.UserDao;
import cn.jzyh.domain.User;
import cn.jzyh.domain.query.UserQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class Select_Lambda {

    @Autowired
    private UserDao userDao;

    //单查询
    @Test
    void testGetAll1() {
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
        //lqw.lt(User::getAge, 500).ge(User::getAge, 50);
        //或者| or 关系
        lqw.lt(User::getAge,120).or().gt(User::getAge,300);
        List<User> userList = userDao.selectList(lqw);
        System.out.println(userList);

    }


    //空值查询
    @Test
    void testGetAll2() {
        //空值查询
        UserQuery uq = new UserQuery();
        uq.setAge(150);
        uq.setAge2(500);

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        //lqw.lt(User::getAge,uq.getAge2());
        //lqw.gt(User::getAge,uq.getAge());

        //判断当前代码是否为true，如果为true连接当前操作
        lqw.lt(null != uq.getAge2(), User::getAge, uq.getAge2())
                .gt(null != uq.getAge(), User::getAge, uq.getAge());

        List<User> userList = userDao.selectList(lqw);
        System.out.println(userList);
    }

    //查询投影，分组查询
    @Test
    void testGetAll3() {
        //方式一，查询结果包含模型类中部分属性
/*        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.select(User::getId,User::getName,User::getAge);*/

        //方式二
/*        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.select("id","name","age");*/

        //方式三，查询结果包含模型类中未定义的属性
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.select("count(*) as count,tel");
        qw.groupBy("tel");
        List<Map<String, Object>> maps = userDao.selectMaps(qw);
        System.out.println(maps);
    }

    //双条件匹配
    @Test
    void testGetAll4() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getName,"张三").eq(User::getTel,"太太");
        User selectOne = userDao.selectOne(lqw);
        System.out.println(selectOne);
    }

    //范围查询
    // Lt Le gt ge eq between
    @Test
    void testGetAll5() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.between(User::getAge,150,500);
        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }

    //模糊查询
    @Test
    void testGetAll6() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        //like %杨%    likeLeft %杨  likeRight 杨%
        lqw.like(User::getName,"杨");
        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }


    //雪花算法测试
    @Test
    void testGetAll7() {
        User user = new User();
        user.setName("王五");
        user.setPassword("12312313");
        user.setAge(71);
        user.setTel("先生");
        userDao.insert(user);
    }

    //乐观锁
    @Test
    void testUpdate(){
        User user = userDao.selectById(1L);
        user.setName("李四");
        userDao.updateById(user);
    }

}