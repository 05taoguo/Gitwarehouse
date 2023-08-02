package cn.jzyh.mapper;

import cn.jzyh.pojo.Bean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<Bean> selectAll();

    void addBrand(Bean brand);

    void deleteAll(@Param("id") Integer id);
}
