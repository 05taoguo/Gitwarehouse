package cn.jzyh.web.servlet.old;

import cn.jzyh.pojo.Brand;
import cn.jzyh.service.BrandService;
import cn.jzyh.service.impl.BrandServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 将请求体的字符集设置为UTF-8
        req.setCharacterEncoding("UTF-8");
        //1.接收数据
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        //2.转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //3.调用service添加
        brandService.add(brand);

        //4.响应成功表示
        res.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }
}
