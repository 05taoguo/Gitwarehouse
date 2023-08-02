package cn.jzyh.web.servlet;


import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.Page;
import cn.jzyh.service.BrandService;
import cn.jzyh.service.impl.BrandServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private final BrandService brandService = new BrandServiceImpl();

    //条件查询
    public void selectA(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //1:获取页数和当前页
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");

        //2:查询条件的获取
        BufferedReader reader = req.getReader();
        String brandStr = reader.readLine();

        //3:查询条件转化
        Brand brand = JSON.parseObject(brandStr, Brand.class);

        //4:调用service
        Page pageBean = brandService.selectCondition(Integer.parseInt(currentPageStr), Integer.parseInt(pageSizeStr), brand);

        //5：把pageBean转化成json字符串
        String pageBeanStr = JSON.toJSONString(pageBean);

        //6:获取流数据，响应到页面
        res.setContentType("text/json;charset=utf-8");
        res.getWriter().write(pageBeanStr);
    }

    //添加或修改
    public void addAllupdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        System.out.println(params);

        Brand brand = JSON.parseObject(params, Brand.class);

        System.out.println(brand);

        Integer id = brand.getId();

        if(id == null){
            brandService.add(brand);
        }else {
            brandService.updateAll(brand);
        }



        res.setContentType("text/json;charset=utf-8");
        res.getWriter().write("success");
    }

    //删除
    public void deleteId(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String id = req.getParameter("id");

        brandService.deleteAll(Integer.parseInt(id));

        res.setContentType("text/json;charset=utf-8");
        res.getWriter().write("success");
    }

}
