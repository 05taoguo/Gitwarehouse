package cn.jzyh.web.servlet;

import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.PageBean;
import cn.jzyh.service.BrandService;
import cn.jzyh.service.impl.BrandServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private final BrandService brandService = new BrandServiceImpl();

    //查询
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用service查询
        List<Brand> brands = brandService.selectAll();

        //2.数据转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3.写数据
        //3.1列表数据有中文
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    //添加或修改
    public void addOrUpdateBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将请求体的字符集设置为UTF-8
        req.setCharacterEncoding("UTF-8");
        //1.接收数据
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        //2.转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        Integer id = brand.getId();
        if(id==null){
            //3.调用service添加
            brandService.add(brand);
        }else {
            //3.更新品牌记录
            brandService.updateBrandById(brand);
        }

        resp.setContentType("text/json;charset=utf-8");

        //4.响应成功表示
        resp.getWriter().write("success");
    }

    //批量删除
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        //1.接收数据
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        //2.转为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //3.调用service删除
        brandService.deleteByIds(ids);

        //4.响应成功表示
        resp.getWriter().write("success");
    }

    //分页
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收 当前页码和每页展示条数 url?currentPage = 1&pageSize
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        //字符串转换为数字
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2.调用service
        PageBean pageBean = brandService.selectPage(currentPage, pageSize);


        //2.数据转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3.写数据
        //3.1列表数据有中文
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    //单条删除
    public void deleteId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        brandService.deleteBid(Integer.parseInt(id));

        resp.getWriter().write("success");
    }

    //条件查询
    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //1:获取页数和当前页
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        //2:查询条件的获取
        BufferedReader reader = request.getReader();
        String brandStr = reader.readLine();

        //3:查询条件转化
        Brand brand = JSON.parseObject(brandStr, Brand.class);

        //4:调用service
        PageBean pageBean = brandService.selectCondition(Integer.parseInt(currentPageStr), Integer.parseInt(pageSizeStr), brand);

        //5：把pageBean转化成json字符串
        String pageBeanStr = JSON.toJSONString(pageBean);

        //6:获取流数据，响应到页面
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(pageBeanStr);
    }

}
