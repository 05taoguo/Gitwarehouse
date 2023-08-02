package cn.jzyh.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration

//@ComponentScan({"cn.jzyh.service","cn.jzyh.dao", "cn.jzyh.dao"})

//安装注解排除
@ComponentScan(value = "cn.jzyh",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class
        ))


public class SpringConfig {
}
