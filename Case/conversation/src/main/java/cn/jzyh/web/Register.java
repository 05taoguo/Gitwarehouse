package cn.jzyh.web;

import cn.jzyh.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")

public class Register extends HttpServlet {
    private RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取账号密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //获取校验码
        String checkCode = req.getParameter("checkCode");

        //从session中获取验证码
        HttpSession session = req.getSession();
        String checkCodeSession = (String) session.getAttribute("checkCodeSession");
        System.out.println("页面的验证码：" + checkCode);
        System.out.println("session验证码：" + checkCodeSession);

        //忽略大小写进行比较
        if (!checkCode.equalsIgnoreCase(checkCodeSession)) {
            req.setAttribute("check_code_err_msg", "验证码错误");

            //跳转到注册页面,并且提示验证码错误
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            //验证码错误终止后续流程，直接转发
            return;
        }

        //调用service判断是否注册成功
        boolean isLoginSuc = registerService.isLoginSucess(username, password);

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        if (isLoginSuc) {
            //储存提示信息
            req.setAttribute("register_arr", "注册成功");
            //转发到登录页面
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else{
            //储存提示信息
            req.setAttribute("register_arr", "已存在");
            //转发到登录页面
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

