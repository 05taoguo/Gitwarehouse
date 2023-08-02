package cn.jzyh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"cn.jzyh.controller", "cn.jzyh.config"})

//开启json转换
@EnableWebMvc
public class SpringMvcConfig {
}
