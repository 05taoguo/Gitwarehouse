package cn.jzyh.mapper;


import cn.jzyh.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {


    //查询
    List<Brand> selectI(@Param("succession") Integer succession,
                        @Param("pageSize") Integer pageSize,
                        @Param("brand") Brand brand
    );

    //总记录数
    Integer tote(Brand brand);

    //添加
    void add(Brand brand);

    //修改
    void updateA(Brand brand);


}
