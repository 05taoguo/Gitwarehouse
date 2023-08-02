import realize.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ts")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("数学","数学","数学","数学","数学"));
        courses.add(new Course("语文","语文","语文","语文","语文"));
        courses.add(new Course("英语","英语","英语","英语","英语"));
        courses.add(new Course("生物","生物","生物","生物","生物"));
        courses.add(new Course("化学","化学","化学","化学","化学"));
        req.setAttribute("courses",courses);
        req.getRequestDispatcher("/Course.jsp").forward(req,res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doGet(req, res);
    }
}
