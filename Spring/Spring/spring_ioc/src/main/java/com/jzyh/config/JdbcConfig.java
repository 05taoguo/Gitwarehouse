package com.jzyh.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.jzyh.service.TestA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration

public class JdbcConfig {

    //    方式一：简单类型
    @Value("com.mysql.jdbc.Driver")
    private String driver;
    @Value("jdbc:mysql://localhost:3306/student")
    private String url;
    @Value("root")
    private String username;
    @Value("020510")
    private String password;

/*    方法获取管理对象
    添加Bean，表示当前方法的返回值是一个bean
    @Bean("dateSource")

    引用类型
    会自动检测，并且进行自动装配
    public DataSource dataSource(TestA testA) {
        System.out.println(testA);*/
    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        return ds;
    }



}
