package cn.jzyh.service;

import cn.jzyh.mapper.UserMapper;
import cn.jzyh.pojo.User;
import cn.jzyh.utils.FactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class RegisterService {
    public boolean isLoginSucess(String username, String password) {
        //1:获取的是sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        //2:获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3:获取mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //4: 据用户名和密码能查询用户
        User user = mapper.select(username, password);

        if (user != null){
            return  false;
        }

        User users = new User();
        users.setUsername(username);
        users.setPassword(password);

        mapper.addUser(users);

        //5:提交事务
        sqlSession.commit();

        //6:关闭资源
        sqlSession.close();

        return true;

    }
}
