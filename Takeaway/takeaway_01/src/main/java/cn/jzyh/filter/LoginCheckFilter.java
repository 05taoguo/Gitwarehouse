package cn.jzyh.filter;

import cn.jzyh.common.BaseContext;
import cn.jzyh.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 检查用户是否登陆，若登陆了，放行
 * */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.确定不需要拦截的路径, 图片，ccs, js, ，放行。
        String requestURI = request.getRequestURI();

        String[] urls = {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        for(String url : urls){
            boolean match = pathMatcher.match(url, requestURI);
            if(match){
                filterChain.doFilter(request,response);
                return;
            }
        }

        // 2.确定哪些路径需要拦截,跳转登录页面重新登录。
        Long employeeid = (Long) request.getSession().getAttribute("employee");

        if (employeeid !=null) {
            log.info("用户已登录，用户id为: {}",request.getSession().getAttribute("employee"));

            BaseContext.setCurrentId(employeeid);

            filterChain.doFilter(request,response);
            return;
        }

        // 2.1.手机端
        Long userid = (Long) request.getSession().getAttribute("user");

        if (userid !=null) {
            log.info("用户已登录，用户id为: {}",request.getSession().getAttribute("user"));

            BaseContext.setCurrentId(userid);

            filterChain.doFilter(request,response);
            return;
        }

        //3.如果未登录的情况，拦截，跳转登录页面重新登录
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

        log.info("拦截,跳转登录页面重新登录");
    }
}