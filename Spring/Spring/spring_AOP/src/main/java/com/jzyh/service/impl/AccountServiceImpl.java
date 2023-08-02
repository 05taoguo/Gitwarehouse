package com.jzyh.service.impl;

import com.jzyh.dao.AccountDao;
import com.jzyh.service.AccountService;
import com.jzyh.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private LogService logService;


    public void transfer(String out,String in ,Double money) throws IOException {

        try {
            //1:从张三账户转出50元
            accountDao.outMoney(out, money);
            if (true) {
                throw new NullPointerException();
            }
            //2:从李四账户转入50元
            accountDao.inMoney(in, money);
        } finally {
            //3：插入转账日志 指定事务的传播行为：开启新事务
            logService.log(out, in, money);
        }

    }

}
