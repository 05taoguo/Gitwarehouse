package cn.jzyh.mapper;

import cn.jzyh.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {

    //查询
    List<Brand> selectAll();

    //新增
    void add(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //分页查询begin页数，size当页条数
    @Select("select * from tb_brand limit #{begin},#{pageSize}")
    List<Brand> selectByPage(@Param("begin") Integer begin,@Param("pageSize") Integer pageSize);

    //查询总记录数
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    //单条删除
    void deleteBid(@Param("id") Integer id);

    //修改
    void updateBrandById(Brand brand);

    //条件查询
    List<Brand> selectByCondition(@Param("begin") Integer begin,
                                  @Param("pageSize") Integer pageSize,
                                  @Param("brand") Brand brand);

    //条件查询，总记录数
    Integer selectCountByCondition(Brand brand);

}
