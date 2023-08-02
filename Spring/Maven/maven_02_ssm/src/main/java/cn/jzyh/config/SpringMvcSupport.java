package cn.jzyh.config;

import cn.jzyh.controller.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Autowired
    private ProjectInterceptor projectInterceptor;
/*    @Autowired
    private ProjectInterceptor projectInterceptor2;*/

    protected void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }

    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books","/books/*");
        // registry.addInterceptor(projectInterceptor2).addPathPatterns("/books","/books/*");

    }

}
