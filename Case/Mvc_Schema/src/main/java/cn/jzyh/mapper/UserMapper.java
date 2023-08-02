package cn.jzyh.mapper;

import cn.jzyh.pojo.Bean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from tb_brand")
    @ResultMap("resultMapId")
    List<Bean> selectAll();

    void addBrand(Bean brand);

    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("resultMapId")
    Bean selectById(@Param("id") Integer id);

    void updateById(Bean brand);

    void deleteById(@Param("id") int id);
}