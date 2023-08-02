package cn.jzyh;

import cn.jzyh.domain.Book;
import cn.jzyh.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringbootSsmApplicationTests {

	@Autowired
	private BookService bookService;
	
	
	@Test
	public void testGetById() {
		Book book = bookService.getById(1);
		System.out.println(book);
	}
	
	@Test
	public void testGetAll(){
		List<Book> all = bookService.getAll();
		System.out.println(all);
	}

}
