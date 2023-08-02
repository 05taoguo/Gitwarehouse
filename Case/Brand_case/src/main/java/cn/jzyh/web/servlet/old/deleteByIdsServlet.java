package cn.jzyh.web.servlet.old;

import cn.jzyh.service.BrandService;
import cn.jzyh.service.impl.BrandServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/deleteByIds")
public class deleteByIdsServlet extends HttpServlet {
    private final BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
        //1.接收数据
        BufferedReader reader = req.getReader();
        String params = reader.readLine();

        //2.转为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        for (int i = 0; i < ids.length; i++) {
            System.out.println("id" + ids[i]);
        }

        //3.调用service删除
        brandService.deleteByIds(ids);

        //4.响应成功表示
        resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }
}
