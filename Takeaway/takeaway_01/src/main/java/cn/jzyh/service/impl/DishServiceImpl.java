package cn.jzyh.service.impl;

import cn.jzyh.dto.DishDto;
import cn.jzyh.entity.Dish;
import cn.jzyh.entity.DishFlavor;
import cn.jzyh.mapper.DishMapper;
import cn.jzyh.service.DishFlavorService;
import cn.jzyh.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 菜品
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    //新增菜品的同时，插入菜品对应的口味数据，dish和dish_flavor
    @Override
    //事务控制-保持一致性
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {

        //保持菜品的基本信息到菜品表dish
        this.save(dishDto);

        //菜品Id
        Long dishId = dishDto.getId();

        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) ->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品的口味数据到菜品表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }
}
