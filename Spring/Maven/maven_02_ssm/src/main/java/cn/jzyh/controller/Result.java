package cn.jzyh.controller;

import lombok.Data;

/*
* 表现层数据的封装模型*/
@Data
public class Result {
    private Integer code;
    private Object data;
    private String msg;

    public Result(){}

    public Result(Integer code,Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code,Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
