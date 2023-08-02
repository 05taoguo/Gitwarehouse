package cn.jzyh.service.impl;

import cn.jzyh.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public void save() {
        System.out.println("Hello,Word");
    }
}
