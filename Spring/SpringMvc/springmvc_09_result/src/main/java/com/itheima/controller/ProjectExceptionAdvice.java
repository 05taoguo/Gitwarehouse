package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//声明异常类
@RestControllerAdvice
public class ProjectExceptionAdvice {

    //记录日志发送运维
    //发送给开发人员
    @ExceptionHandler(BusinessException.class)
    public Result BusinessException(BusinessException ex) {
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    public Result SystemException(SystemException ex) {
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {

        return new Result(Code.SYSTEM_UNKNOW_ERR,null,"系统繁忙！！！");
    }
}
