package cn.jzyh.controller;

import cn.jzyh.exception.BusinessException;
import cn.jzyh.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* Rest风格声明异常类，异常处理器*/
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //系统异常
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    //业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){
        //规范返回结构
        return new Result(ex.getCode(),null,ex.getMessage());
    }


    //其他异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        return new Result(Code.SYSTEM_TIMEOUT_ERR,null,"系统繁忙，稍后重试！");
    }
}
