package cn.jzyh;

import cn.jzyh.dao.UserDao;
import cn.jzyh.domain.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;

    //    新增
    @Test
    void insert_all(){
        User user = new User();
        user.setName("jzyh");
        user.setPassword("331231");
        user.setTel("轻轻");
        user.setAge(22);
        userDao.insert(user);
    }

    //    删除
    @Test
    void delete_all(){
        userDao.deleteById(1686653353915969538L);
    }

    //    修改
    @Test
    void update_all(){
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        userDao.updateById(user);
    }

    //查询单个
    @Test
    void selectId_all(){
        User user = userDao.selectById(1L);
        System.out.println(user);
    }

    //    分页查询,配置拦截器
    @Test
    void GetByPage(){
        IPage page = new Page(1,5);
        userDao.selectPage(page,null);
        System.out.println("当前页码值" + page.getCurrent());
        System.out.println("每页显示条数" + page.getSize());
        System.out.println("一共多少页" + page.getPages());
        System.out.println("一共多少条数据" + page.getTotal());
        System.out.println("数据" + page.getRecords());
    }

    @Test
    void testGetAll() {
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }

}
