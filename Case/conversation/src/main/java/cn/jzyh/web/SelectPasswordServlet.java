package cn.jzyh.web;
import cn.jzyh.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet("/SelectPasswordServlet")
public class SelectPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String password = req.getParameter("password");

        System.out.println("密码" + password);

        boolean result = password.length()==6;

        System.out.println("返回值" + result);

        //2：返回数据
        res.getWriter().write("" + result);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }
}