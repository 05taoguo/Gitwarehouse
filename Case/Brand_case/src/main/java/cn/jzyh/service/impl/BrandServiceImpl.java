package cn.jzyh.service.impl;

import cn.jzyh.mapper.BrandMapper;
import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.PageBean;
import cn.jzyh.service.BrandService;
import cn.jzyh.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1.创建sqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //查询
    @Override
    public List<Brand> selectAll() {
        //2.获取sqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        List<Brand> brands = mapper.selectAll();

        //5.释放资源
        sqlSession.close();

        return brands;

    }

    //新增
    @Override
    public void add(Brand brand) {
        //2.获取sqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.add(brand);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();

    }

    //批量删除
    @Override
    public void deleteByIds(int[] ids) {
        //2.获取sqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.deleteByIds(ids);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }

    //索引
    @Override
    public PageBean selectPage(Integer currentPage, Integer pageSize) {
        //2.获取sqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.计算开始的索引
        Integer begin = (currentPage - 1) * pageSize;
        //计算查询条目数

        //5.查询当前也数据
        List<Brand> rows = mapper.selectByPage(begin, pageSize);

        //6.查询总记录数
        int totalCount = mapper.selectTotalCount();

        //7.封装PageBean
        PageBean pageBean = new PageBean();

        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8.资源释放
        sqlSession.close();

        return pageBean;
    }

    //单条删除
    @Override
    public void deleteBid(Integer id) {
        //2.获取sqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.deleteBid(id);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }

    //修改品牌
    @Override
    public void updateBrandById(Brand brand) {
        //2.获取sqlSession对象
        SqlSession sqlSession = factory.openSession();

        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4.调用方法
        mapper.updateBrandById(brand);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }

    //条件查询
    @Override
    public PageBean selectCondition(Integer currentPage, Integer pageSize, Brand brand) {
        //1:获取的是SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2：获取sqlSession对象,openSession() 如果括号为空，手动提交session
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3：获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4: 封装数据到pageBean中
        //4.1分页查询
        //计算起始索引= (当前页-1)*页数
        Integer begin = (currentPage - 1) * pageSize;

        //4.2条件查询处理
        String brandName = brand.getBrandName();
        brandName = "%" + brandName + "%";
        String companyName = brand.getCompanyName();
        companyName = "%" + companyName + "%";

        //4.3查询条件赋值
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        //4.4查询
        List<Brand> brands = mapper.selectByCondition(begin, pageSize, brand);

        //4.5 总记录数
        Integer count = mapper.selectCountByCondition(brand);

        //4.6 封装到pageBean对象中
        PageBean pageBean = new PageBean();
        pageBean.setRows(brands);
        pageBean.setTotalCount(count);

        //5:关闭资源
        sqlSession.close();
        return pageBean;
    }

}
