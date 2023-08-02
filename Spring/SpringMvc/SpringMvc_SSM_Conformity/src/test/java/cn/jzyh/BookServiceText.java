package cn.jzyh;

import cn.jzyh.config.SpringConfig;
import cn.jzyh.domain.Book;
import cn.jzyh.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/*整合jut测试*/
@RunWith(SpringJUnit4ClassRunner.class)

/*配置类，测试环境*/
@ContextConfiguration(classes = SpringConfig.class)
public class BookServiceText {
    @Autowired
    private BookService bookService;

    @Test
    public void testGetById(){
        Book byId = bookService.getById(1);
        System.out.println(byId);

    }

    @Test
    public void testGetById2(){
        List<Book> all = bookService.getAll();
        System.out.println(all);
    }
}
