package cn.jzyh.service;

import cn.jzyh.mapper.UserMapper;
import cn.jzyh.pojo.Bean;
import cn.jzyh.pojo.User;
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

    public Bean getBrandById(Integer id) {
        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Bean bean = mapper.selectById(id);

        sqlSession.commit();

        sqlSession.close();

        return bean;
    }

    public void updateBand(Bean brand) {
        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updateById(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    public void deleteBand(int id) {
        SqlSessionFactory sqlSessionFactory = FactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteById(id);

        sqlSession.commit();

        sqlSession.close();
    }

}
