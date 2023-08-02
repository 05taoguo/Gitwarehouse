package cn.jzyh;

import cn.jzyh.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@SpringBootTest(classes = SpringbootJunit01Application.class)
class SpringbootJunit01ApplicationTests {

	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {
		bookService.save();
	}

	@Test
	public void save(){
		bookService.save();
	}

}
