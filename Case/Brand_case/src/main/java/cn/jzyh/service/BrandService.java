package cn.jzyh.service;

import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {


//    查询所有
    List<Brand> selectAll();

//    新增数据
    void add(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //分页查询，currentPage 当前页面，pageSize 每页展示条数
    PageBean selectPage(Integer currentPage,Integer pageSize);

    //单行删除
    void deleteBid(Integer id);

    //修改
    void updateBrandById(Brand brand);

    //条件查询
    PageBean selectCondition(Integer currentPage, Integer pageSize, Brand brand);

}
