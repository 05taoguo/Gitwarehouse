package cn.jzyh.service.impl;

import cn.jzyh.common.CustomException;
import cn.jzyh.dto.DishDto;
import cn.jzyh.entity.Dish;
import cn.jzyh.entity.DishFlavor;
import cn.jzyh.mapper.DishMapper;
import cn.jzyh.service.DishFlavorService;
import cn.jzyh.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品
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
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品的口味数据到菜品表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }


    //根据Id查询菜品信息和对应的口味信息
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息，dish表差
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();

        BeanUtils.copyProperties(dish, dishDto);

        //查询菜品对应的口味信息，从dish_flavors
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getId, dish.getId());

        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(flavors);

        return dishDto;
    }


    //更新菜品信息，同时更新口味信息
    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);

        //清理当前菜品对应的口味数据-dish_flavor表delete
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());

        dishFlavorService.remove(queryWrapper);

        //添加当前菜品对应的口味数据-dish_flavor表insert
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

    }


    /*
     * 删除菜品*/
    @Override
    public void removeById(List<Long> ids) {
        //查询套餐状态是否可以删除
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId, ids);
        queryWrapper.eq(Dish::getStatus, 1);

        int count = this.count(queryWrapper);

        if (count > 0) {
            throw new CustomException("菜品启售中，禁止删除");
        }

        // 删除菜品
        super.removeByIds(ids);
    }
}
