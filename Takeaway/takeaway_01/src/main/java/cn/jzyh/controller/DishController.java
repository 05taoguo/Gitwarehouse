package cn.jzyh.controller;

import cn.jzyh.common.CustomException;
import cn.jzyh.common.R;
import cn.jzyh.dto.DishDto;
import cn.jzyh.entity.Category;
import cn.jzyh.entity.Dish;
import cn.jzyh.entity.DishFlavor;
import cn.jzyh.entity.Setmeal;
import cn.jzyh.service.CategoryService;
import cn.jzyh.service.DishFlavorService;
import cn.jzyh.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
* 菜品管理*/
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    /*
    * 新增菜品*/
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        //log.info("{}", dishDto);

        dishService.saveWithFlavor(dishDto);

        return R.success("新增菜品成功");
    }


    /*
    * 菜品信息的分页*/
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){

        //分页构造器
        Page<Dish> dishPage = new Page<>(page,pageSize);

        Page<DishDto> dishDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件
        queryWrapper.like(name != null,Dish::getName,name);

        //排序
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //分页查询
        dishService.page(dishPage, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(dishPage,dishDtoPage,"records");

        List<Dish> records = dishPage.getRecords();

        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item,dishDto);

            //分类id
            Long categoryId = item.getCategoryId();

            //根据Id查询分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null){
                String categoryName = category.getName();

                dishDto.setCategoryName(categoryName);
            }

            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return R.success(dishDtoPage);
    }

    /*
    * 根据Id修改菜品信息和口味信息*/
    @GetMapping("/{id}")
    public R<DishDto> getId(@PathVariable Long id){

        DishDto dishDto = dishService.getByIdWithFlavor(id);

        return R.success(dishDto);
    }

    /*
    * 更新菜品信息，同时更新口味信息*/
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){

        dishService.updateWithFlavor(dishDto);

        return R.success("更新菜品成功");
    }


    /*
    * 根据条件查询对应的菜品数据*/
/*    @GetMapping("/list")
    public R<List<Dish>> list(Dish dish){

        //查询条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null,Dish::getCategoryId,dish.getCategoryId());

        //查询状态为1，起售状态
        queryWrapper.eq(Dish::getStatus,1);

        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);

        return R.success(list);
    }*/

    /*
    * 用户，口味规格选择*/
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){

        //查询条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null,Dish::getCategoryId,dish.getCategoryId());

        //查询状态为1，起售状态
        queryWrapper.eq(Dish::getStatus,1);

        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);

        List<DishDto> dishDtoList = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item,dishDto);

            //分类id
            Long categoryId = item.getCategoryId();

            //根据Id查询分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null){
                String categoryName = category.getName();

                dishDto.setCategoryName(categoryName);
            }

            //当前菜品id
            Long dishId = item.getId();

            //口味
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId,dishId);

            //口味的集合
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);

            dishDto.setFlavors(dishFlavorList);

            return dishDto;
        }).collect(Collectors.toList());

        return R.success(dishDtoList);
    }



    /*
    * 根据Id删除菜品*/
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        dishService.removeById(ids);
        return R.success("删除菜品成功");
    }


    /*
    * 菜品状态修改
    * 停售起售，状态设置*/
    @PostMapping("/status/{status}")
    public R<String> status(@PathVariable int status,@RequestParam List<Long> ids){
/*        LambdaUpdateWrapper<Dish> queryWrapper = new LambdaUpdateWrapper<>();

        queryWrapper.set(Dish::getStatus, status);
        queryWrapper.in(Dish::getId,ids);

        dishService.update(queryWrapper);*/

        for (Long id : ids) {
            Dish dish = dishService.getById(id);
            dish.setStatus(status);

            dishService.updateById(dish);
        }

        return R.success("状态修改成功");
    }

}
