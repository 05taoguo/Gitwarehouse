package cn.jzyh.web;

import cn.jzyh.pojo.Bean;
import cn.jzyh.service.BrandService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/add_servlet")
public class AddBrandServlet extends HttpServlet {
    private BrandService brandService = new BrandService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 将请求体的字符集设置为UTF-8
        req.setCharacterEncoding("UTF-8");

        //获取请求体
        BufferedReader reader = req.getReader();
        //读一行
        String params = reader.readLine();
        System.out.println(params);

        // 将JSON字符串转为Java对象
        Bean bean = JSON.parseObject(params, Bean.class);
        System.out.println(bean);

        // 调用BrandService对象保存数据
        BrandService brandService = new BrandService();
        brandService.add(bean);

        res.getWriter().write("success");
    }
}
