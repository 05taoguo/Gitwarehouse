package cn.jzyh.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//创建springmvc配置文件，加载controller对应的bean
@Configuration
@ComponentScan("cn.jzyh.controller")
public class SpringMvcConfig {
}
