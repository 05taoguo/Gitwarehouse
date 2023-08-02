package cn.jzyh.config;

import cn.jzyh.service.impl.AccountServiceImpl;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JdbcConfig {

    //    方式一：简单类型
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    /*    方法获取管理对象
    添加Bean，表示当前方法的返回值是一个bean
    @Bean("dateSource")

    方式二 :引用类型
    会自动检测，并且进行自动装配
    public DataSource dataSource(TestA testA) {
        System.out.println(testA);*/
    @Bean
    public DataSource dataSource(AccountServiceImpl accountServiceImpl) {

        System.out.println("accountService:" + accountServiceImpl);
        System.out.println("driver:" + driver);

        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }



}
