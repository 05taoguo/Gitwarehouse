package cn.jzyh.web;

import cn.jzyh.service.impl.BrandServiceImpl;
import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.PageBean;
import cn.jzyh.service.BrandService;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    BrandService brandService = new BrandServiceImpl();

    //查询
    public void selectAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");

        BufferedReader reader = req.getReader();
        String brands = reader.readLine();

        Brand brand = JSON.parseObject(brands, Brand.class);

        PageBean pageBean = brandService.selectAll(Integer.parseInt(currentPage), Integer.parseInt(pageSize),brand);

        String jsonString = JSON.toJSONString(pageBean);

        res.setContentType("text/json;charset=utf-8");

        res.getWriter().write(jsonString);

    }

    //添加或修改
    public void addAllupdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        BufferedReader reader = req.getReader();
        String brands = reader.readLine();

        Brand brand = JSON.parseObject(brands, Brand.class);

        Integer id = brand.getId();
        System.out.println("已知" + id);

        if(id==null){
            brandService.add(brand);
            System.out.println("空" + id);
        }else {
            brandService.updateA(brand);
            System.out.println("有" + id);
        }

        res.setContentType("text/json;charset=utf-8");

        res.getWriter().write("success");

    }


}
