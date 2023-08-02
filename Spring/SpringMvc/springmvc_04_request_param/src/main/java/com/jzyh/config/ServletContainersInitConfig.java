package com.jzyh.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //乱码处理
    //过滤器
    @Override
    protected Filter[] getServletFilters() {
        //字符过滤
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        //设定字符集
        filter.setEncoding("UTF-8");
        return new Filter[]{filter};

/*        多个过滤器
        return new Filter[]{filter1，filter2，filter3};*/
    }
}
