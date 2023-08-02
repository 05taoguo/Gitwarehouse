package Member;

import BeanClass.Bean;
import UserMapper1_0.mybatis.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/logon")
public class Logon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
        username = new String(bytes,StandardCharsets.UTF_8);

        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Bean bean = mapper.SelectUser(username,password);

        //解决响应乱码
        resp.setContentType("text/html;charset=utf-8");
        //获取输出流
        PrintWriter writer = resp.getWriter();

        //3:如果能查询到用户，登录成功
        if (bean != null) {
            writer.write("登录成功");
        } else {
            //4:如果没有查询到用户，登录失败
            writer.write("登录失败");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


}
