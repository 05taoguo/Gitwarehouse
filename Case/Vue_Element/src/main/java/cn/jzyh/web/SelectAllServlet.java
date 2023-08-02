package cn.jzyh.web;

import cn.jzyh.pojo.Bean;
import cn.jzyh.service.BrandService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //1.调用查询
        List<Bean> brands = brandService.selectAll();

        //1.1存储处理后的对象
        List<Bean> brandList = new ArrayList<>();
        //1.1.1对集合中的元素遍历
        brands.forEach(brand -> {
            //1.1.2判断每个对象的status（状态属性）是否为空或者不等于1，如果是statusName设置为"开启"，反之statusName设置为"禁止"。
            String statusName = (brand.getStatus() == null || brand.getStatus() != 1) ? "禁止" : "开启";
            //1.1.3检查statusName元素的状态，没有属性添加，有覆盖
            brand.setStatusName(statusName);

            //1.1.4添加brand对象到brandList列表中
            brandList.add(brand);
        });

        //2.集合序列化
        String jsonString = JSON.toJSONString(brands);

        //3.1解决乱码
        res.setContentType("text/json;charset=UTF-8");

        //3.2响应数据
        res.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }
}
