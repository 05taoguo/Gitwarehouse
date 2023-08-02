package cn.jzyh.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 根据url不同 请求不同的业务功能（不同的方法）
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //不在希望根据请求方式区分不同的业务功能，所以我们重写service方法，覆盖父类HttpServlet的service方法

        //1:获取请求的路径
        String requestURI = req.getRequestURI();//http://localhost:8080/brand_case/brand/selectAll

        //2:获取路径的最后selectAll
        int index = requestURI.lastIndexOf("/");
        String methodName = requestURI.substring(index + 1);
        //System.out.println("methodName:"+methodName);

        //3:反射获取对应的方法名称
        //3.1获取Class对象
        // this.getClass();
        //this 代表谁？ BaseServlet ？BrandServlet ? HttpServlet
        //this =>谁调用我(this在哪里?在service方法里，谁调用了service方法?  this就是谁)，我就是谁
        System.out.println(this);
        Class<? extends BaseServlet> aClass = this.getClass();

        //3.2获取方法对象
        try {
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            try {

                //3.3调用方法
                method.invoke(this, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }

//    public abstract void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
