package com.jzyh.service.impl;

import com.jzyh.dao.LogDao;
import com.jzyh.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void log(String out, String in, Double money) {
        logDao.log("转载操作：转帐方-" + out + "，收款方-" + in + "，金额：" + money);
    }
}
