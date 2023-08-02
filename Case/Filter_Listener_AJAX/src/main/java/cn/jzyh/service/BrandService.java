package cn.jzyh.service;

import cn.jzyh.mapper.UserMapper;
import cn.jzyh.pojo.Bean;
import cn.jzyh.utils.FactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    public List<Bean> selectAll(){
        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<Bean> brands = mapper.selectAll();

        sqlSession.close();

        return brands;
    }

    public void add(Bean brand) {
        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.addBrand(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    public void deleteBand(Integer id) {
        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteAll(id);

        sqlSession.commit();

        sqlSession.close();

    }
}
