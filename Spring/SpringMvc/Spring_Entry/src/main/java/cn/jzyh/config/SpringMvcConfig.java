package cn.jzyh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration

@ComponentScan("cn.jzyh.controller")

//开启类型转换：json =》 object
@EnableWebMvc
public class SpringMvcConfig {
}
