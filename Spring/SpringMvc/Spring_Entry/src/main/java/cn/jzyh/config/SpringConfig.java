package cn.jzyh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration

//加载，过滤controller的类
@ComponentScan(value = "cn.jzyh",
                excludeFilters = @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = Controller.class
                ))



public class SpringConfig {
}
