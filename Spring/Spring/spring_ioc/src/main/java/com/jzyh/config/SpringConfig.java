package com.jzyh.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

//配置资源
@Configuration

//扫描文件
@ComponentScan("com.jzyh")

/*多个
@ComponentScan({"com.jzyh","com.jzyh"})*/

//扫描String资源
@PropertySource("classpath:jdbc.properties")

/*多个
@PropertySource({"jdbc.properties","jdbc.properties"})*/

//方式一：精准导入配置jdbc
//方式二：扫描@ComponentScan
//多个参考上方，使用大括号
@Import(JdbcConfig.class)

public class SpringConfig {
}
