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

@WebServlet("/register")

public class Register extends HttpServlet {
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

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        if(bean != null){
            writer.write("已存在");
        }else{
            Bean bean1 = new Bean();
            bean1.setUsername(username);
            bean1.setPassword(password);

            int su = mapper.inertUser(username,password);
            if (su>0){
                writer.write("注册 成功");
            }

            sqlSession.commit();
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
