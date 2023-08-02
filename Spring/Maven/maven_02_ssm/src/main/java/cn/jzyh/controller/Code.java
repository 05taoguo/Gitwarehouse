package cn.jzyh.controller;

/*
* 状态码*/
public class Code {
    /*
    * 成功码*/
    public static final Integer SAVE_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer GET_OK = 20041;

    /*
    * 失败码*/
    public static final Integer SAVE_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer GET_ERR = 20040;


    //系统错误
    public static final Integer SYSTEM_ERR = 50001;

    //业务错误
    public static final Integer SYSTEM_TIMEOUT_ERR = 50002;

    //其他异常
    public static final Integer SYSTEM_UNKOW_ERR = 59999;

    public static final Integer BUSINESS_ERR = 60002;


}
