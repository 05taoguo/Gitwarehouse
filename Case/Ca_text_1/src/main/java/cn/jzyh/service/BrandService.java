package cn.jzyh.service;

import cn.jzyh.pojo.Brand;
import cn.jzyh.pojo.Page;

public interface BrandService {

    Page selectCondition(Integer currentPage, Integer pageSize, Brand brand);

    void add(Brand brand);

    //删除
    void deleteAll(Integer id);

    //修改
    void updateAll(Brand brand);
}
