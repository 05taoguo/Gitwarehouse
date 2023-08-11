package cn.jzyh.service.impl;

import cn.jzyh.entity.Dish;
import cn.jzyh.mapper.DishMapper;
import cn.jzyh.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 菜品
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
