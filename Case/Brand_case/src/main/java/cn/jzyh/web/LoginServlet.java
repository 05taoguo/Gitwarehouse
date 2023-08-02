package cn.jzyh.web;

import cn.jzyh.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1：获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //是否登录
        boolean isLoginSuc = loginService.isLoginSucess(username, password);

        //解决请求乱码
        response.setContentType("text/html;charset=utf-8");
        // 判断是否登录
        if (isLoginSuc) {
            //获取session对象 ,把用户名存在session域中
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            String remember = request.getParameter("remember");
            if("1".equals(remember)){
                //创建cookie对象
                Cookie c_username = new Cookie("c_username",username);
                Cookie c_password = new Cookie("c_password",password);

                //设置cookie的用户名和密码一周的时间
                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);

                //发送到浏览器
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //重定向到品牌列表的servlet
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/brand.html");
        } else {
            //跳转到登录页面，提示用户名或者密码错误
            request.setAttribute("errorMsg", "用户名或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
