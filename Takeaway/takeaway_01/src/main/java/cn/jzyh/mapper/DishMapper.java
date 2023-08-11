package cn.jzyh.mapper;

import cn.jzyh.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 菜品
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
