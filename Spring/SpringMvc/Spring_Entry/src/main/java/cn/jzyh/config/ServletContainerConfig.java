//package cn.jzyh.config;
//
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
//
//public class ServletContainerConfig extends AbstractDispatcherServletInitializer {
//
//    //创建SpringMvc容器
//    protected WebApplicationContext createServletApplicationContext() {
//        //创建容器
//        AnnotationConfigWebApplicationContext act = new AnnotationConfigWebApplicationContext();
//
//        //SpringMvcConfig配置类组测到SpringMvc容器中
//        act.register(SpringConfig.class);
//
//        //返回容器
//        return act;
//    }
//
//    //请求的路径,/表示所有的请求交给springMvc处理
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//
//    protected WebApplicationContext createRootApplicationContext() {
//        return null;
//    }
//}
