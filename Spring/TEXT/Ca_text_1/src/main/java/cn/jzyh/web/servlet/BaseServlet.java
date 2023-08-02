package cn.jzyh.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    //根据资源路径进行方法分发
    @Override
    //protected
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //1.获取请求路径
        ///web/brand/selectAll
        String requestURI = req.getRequestURI();

        //2.获取最后一段路径,方法名
        int index = requestURI.lastIndexOf('/');
        String methodName = requestURI.substring(index + 1);

        //3.执行方法
        //获取BrandServlet / UserServlet 字节码对象class
        Class<? extends BaseServlet> cls = this.getClass();

        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,res);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
