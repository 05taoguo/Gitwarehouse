package cn.jzyh.mapper;

import cn.jzyh.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {

    //条件查询
    List<Brand> selectByCondition(@Param("begin") Integer begin,
                                  @Param("pageSize") Integer pageSize,
                                  @Param("brand") Brand brand);

    //条件查询，总记录数
    Integer selectCountByCondition(Brand brand);

    //添加
    void add(Brand brand);

    //删除
    void deleteAll(@Param("id") Integer id);

    //修改
    void updateAll(Brand brand);

}
