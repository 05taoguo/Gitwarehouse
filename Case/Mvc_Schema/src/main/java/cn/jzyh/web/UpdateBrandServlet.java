package cn.jzyh.web;

import cn.jzyh.pojo.Bean;
import cn.jzyh.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/update_servlet")
public class UpdateBrandServlet extends HttpServlet {
    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String brandName = req.getParameter("brandName");
        String companyName = req.getParameter("companyName");
        String ordered = req.getParameter("ordered");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        String id = req.getParameter("id");

        Bean brand = new Bean();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.valueOf(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.valueOf(status));
        brand.setId(Integer.valueOf(id));

        brandService.updateBand(brand);

        req.getRequestDispatcher("/all_servlet").forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }
}
