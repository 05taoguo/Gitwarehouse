package cn.jzyh;

import cn.jzyh.dao.UserDao;
import cn.jzyh.domain.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Select_Page4 {

    @Autowired
    private UserDao userDao;

    //    删除单条/多条
    @Test
    void delete_all() {
        //删除单条
        //userDao.deleteById(1686653353915969538L);

        //删除多条
        List<Long> longs = new ArrayList<>();
        longs.add(1687085719100641282L);
        longs.add(1687381591319420929L);
        userDao.deleteBatchIds(longs);
    }

    //查询单个/多个
    @Test
    void selectId_all() {
        //查询单个
        //userDao.selectById(1L);

        //查询多个
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        longs.add(3L);
        userDao.selectBatchIds(longs);
    }

    //    分页查询,配置拦截器
    @Test
    void GetByPage() {
        IPage page = new Page(1, 5);
        userDao.selectPage(page, null);
        System.out.println("当前页码值" + page.getCurrent());
        System.out.println("每页显示条数" + page.getSize());
        System.out.println("一共多少页" + page.getPages());
        System.out.println("一共多少条数据" + page.getTotal());
        System.out.println("数据" + page.getRecords());
    }

}
