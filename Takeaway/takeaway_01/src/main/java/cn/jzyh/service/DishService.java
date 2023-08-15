package cn.jzyh.service;

import cn.jzyh.dto.DishDto;
import cn.jzyh.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 菜品
 */
public interface DishService extends IService<Dish> {

    //新增菜品的同时，插入菜品对应的口味数据，dish和dish_flavor
    void saveWithFlavor(DishDto dishDto);

    //根据Id查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新口味信息
    void updateWithFlavor(DishDto dishDto);

    }
