package cn.jzyh.web;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class MyFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截成功");
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String[] urls = {"css/", "imgs/", "login.jsp", "register.jsp", "/login", "/register", "/CodeGenServlet","/SelectPasswordServlet"};
        String url = req.getRequestURI().toString();
        for (String u : urls){
            if (url.contains(u)){
                filterChain.doFilter(req,servletResponse);
                return;
            }
        }

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if(username != null && username != ""){
            filterChain.doFilter(req,servletResponse);
            return;
        }

        req.setAttribute("errorMsg","尚未登陆");
        req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
