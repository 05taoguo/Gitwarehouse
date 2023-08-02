package cn.jzyh.service.impl;

import cn.jzyh.mapper.BrandMapper;
import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.PageBean;
import cn.jzyh.service.BrandService;
import cn.jzyh.util.SqlSessionFactoryUtils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class BrandServiceImpl implements BrandService {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

//        条件查询
        @Override
        public PageBean selectAll(Integer ALL, Integer size, Brand brand) {
                SqlSession sqlSession = sqlSessionFactory.openSession();
                BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

                Integer all = (ALL -1 ) * size;

                String brandName = brand.getBrandName();
                brandName = "%" + brandName + "%";
                String companyName = brand.getCompanyName();
                companyName = "%" + companyName + "%";

                brand.setBrandName(brandName);
                brand.setCompanyName(companyName);

                List<Brand> brands = mapper.selectI(all, size, brand);

                Integer tote = mapper.tote(brand);

                PageBean pageBean = new PageBean();
                pageBean.setRows(brands);
                pageBean.setTotalCount(tote);

                sqlSession.close();

                return pageBean;
        }

//        添加
        @Override
        public void add(Brand brand) {
                SqlSession sqlSession = sqlSessionFactory.openSession();
                BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

                mapper.add(brand);

                sqlSession.commit();

                sqlSession.close();
        }

        @Override
        public void updateA(Brand brand) {
                SqlSession sqlSession = sqlSessionFactory.openSession();
                BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

                mapper.updateA(brand);

                sqlSession.commit();

                sqlSession.close();
        }
}
