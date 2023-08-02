package cn.jzyh.service.impl;

import cn.jzyh.mapper.BrandMapper;
import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.Page;
import cn.jzyh.service.BrandService;
import cn.jzyh.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //条件查询
    @Override
    public Page selectCondition(Integer currentPage, Integer pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //获取起始索引页
        Integer begin = (currentPage - 1) * pageSize;

        //条件查询模糊
        String brandName = brand.getBrandName();
        brandName = "%" + brandName + "%";
        String companyName = brand.getCompanyName();
        companyName = "%" + companyName + "%";

        //赋值
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);

        //查询
        List<Brand> brands = mapper.selectByCondition(begin, pageSize, brand);

        //总记录数
        Integer total = mapper.selectCountByCondition(brand);

        //封装起始索引页的查询和总记录数
        Page page = new Page();
        page.setRow(brands);
        page.setTotalCount(total);

        sqlSession.close();
        //返回
        return page;
    }

    //添加
    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    //删除
    @Override
    public void deleteAll(Integer id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.deleteAll(id);

        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void updateAll(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.updateAll(brand);

        sqlSession.commit();

        sqlSession.close();
    }


}
