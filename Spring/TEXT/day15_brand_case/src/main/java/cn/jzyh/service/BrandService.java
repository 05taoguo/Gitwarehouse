package cn.jzyh.service;

import cn.jzyh.pojo.PageBean;
import cn.jzyh.pojo.Brand;

/**
 * 品牌对应的接口
 */
public interface BrandService {
    //查询
    PageBean selectAll(Integer ALL, Integer size, Brand brand);

    //添加
    void add(Brand brand);

    //修改
    void updateA(Brand brand);
}
