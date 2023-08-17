package cn.jzyh.util;

/**
 * 基于ThreadLocal实现的员工Id操作
 */
public class BaseContext {

    //存储员工Id的ThreadLocal变量
    public static ThreadLocal<Long> ID_THREAD_LOCAL = new ThreadLocal<>();

    //设置员工id到threadLocal中
    public static void setCurrentId(Long id) {
        ID_THREAD_LOCAL.set(id);
    }

    //从threadLocal中获取员工id
    public static Long getCurrentId() {
        return ID_THREAD_LOCAL.get();
    }
}