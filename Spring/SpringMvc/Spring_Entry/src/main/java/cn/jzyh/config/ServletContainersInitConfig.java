package cn.jzyh.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    //加载容器
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    //指定MVC
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    //使用“/”拉取
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //解决中文乱码问题
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("utf-8");
        return new Filter[]{cef};

    }
}
