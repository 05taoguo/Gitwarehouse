package cn.jzyh;

import cn.jzyh.dao.UserDao;
import cn.jzyh.domain.User;
import cn.jzyh.domain.query.UserQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Select_Page1 {

    @Autowired
    private UserDao userDao;

    //查询全部数据
    @Test
    public void GeiAll() {
        List<User> selectList = userDao.selectList(null);
        System.out.println(selectList);
    }

    //根据id查询
    @Test
    public void GeiId() {
        userDao.selectById(1L);
    }

    //添加单条数据
    @Test
    public void Insert_All() {
        User user = new User();
        user.setName("张三");
        user.setPassword("123456");
        user.setAge(17);
        user.setTel("教师");
        userDao.insert(user);
    }

    //修改单条数据
    @Test
    public void Update_All() {
        User user = new User();
        user.setId(1687036784399138817L);
        user.setName("张大");
        user.setPassword("123456");
        user.setAge(17);
        user.setTel("教师");
        userDao.updateById(user);
    }

    //删除单条数据
    @Test
    public void Delete_All() {
        userDao.deleteById(1687085719100641282L);
    }

    //分页查询
    @Test
    public void Page() {
        IPage<User> iPage = new Page(1, 2);
        IPage<User> iPage1 = userDao.selectPage(iPage, null);

        System.out.println("总条数" + iPage1.getTotal());
        System.out.println("分几页" + iPage1.getPages());

        System.out.println("当前页" + iPage1.getCurrent());
        System.out.println("每页条数" + iPage1.getSize());
        System.out.println("当页数据" + iPage1.getRecords());

    }

    //方法一：条件查询
    @Test
    public void Select_al() {
        QueryWrapper qw = new QueryWrapper();
        //qw.lt("age", 300);
        qw.gt("age", 500);
        List<User> users = userDao.selectList(qw);
        System.out.println(users);

    }

    //方法二：条件查询
    @Test
    public void Select_al2() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().lt(User::getAge,100).or().gt(User::getAge,700);
        List<User> users = userDao.selectList(qw);
        System.out.println(users);

    }

    //方法三：条件查询
    @Test
    public void Select_al3() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.lt(User::getAge,200).or().gt(User::getAge,700);
        List<User> users = userDao.selectList(lqw);
        System.out.println(users);

    }

    //空值查询
    @Test
    public void Select_I(){
         /*     第一种方式：
        if(queryUser.getAge() !=null){
            queryWrapper.ge(User::getAge,queryUser.getAge());
        }
        if(queryUser.getAge2() !=null){
            queryWrapper.lt(User::getAge,queryUser.getAge2());
        }*/
        /* 第二种方式：
         queryWrapper.ge(queryUser.getAge() !=null,User::getAge,queryUser.getAge());
        queryWrapper.lt(queryUser.getAge2()!=null ,User::getAge,queryUser.getAge2());*/


        UserQuery userQuery = new UserQuery();
        userQuery.setAge(200);
        userQuery.setAge2(500);

        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        lqw.lt(null != userQuery.getAge2(),User::getAge,userQuery.getAge2())
                .gt(null != userQuery.getAge(),User::getAge,userQuery.getAge());

        List<User> users = userDao.selectList(lqw);
        System.out.println(users);
    }

}
