package cn.jzyh.service.impl;

import cn.jzyh.controller.Code;
import cn.jzyh.dao.BookDao;
import cn.jzyh.domain.Book;
import cn.jzyh.exception.SystemException;
import cn.jzyh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public boolean save(Book book) {
        return bookDao.save(book) > 0;
    }

    public boolean update(Book book) {
        return bookDao.update(book)  > 0;
    }

    public boolean delete(Integer id) {
        return bookDao.delete(id)  > 0;
    }

    public Book getById(Integer id) {
/*
        模拟异常
        if(id == 1){
            throw new SystemException(Code.BUSINESS_ERR,"请不要挑战");
        }

        try{
            int i = 1/0;
        }catch(Exception e){
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR,"服务器访问超时");
        }*/

        return bookDao.getById(id);
    }

    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
