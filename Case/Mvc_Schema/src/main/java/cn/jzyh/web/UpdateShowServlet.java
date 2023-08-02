package cn.jzyh.web;

import cn.jzyh.pojo.Bean;
import cn.jzyh.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/update_show")
public class UpdateShowServlet extends HttpServlet {
    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");

        Bean brand = brandService.getBrandById(Integer.valueOf(id));

        req.setAttribute("brand", brand);

        req.getRequestDispatcher("/update_brand.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }
}
