package cn.jzyh.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//定义servlet容器启动的配置类，加载是spring配置
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {

    //加载SpringMvc容器启动
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class);
        return ctx;
    }

    //设置哪些请求归属springmvc管理
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //加载spring容器配置
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
