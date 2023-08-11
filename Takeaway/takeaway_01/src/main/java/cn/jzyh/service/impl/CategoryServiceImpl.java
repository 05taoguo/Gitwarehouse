package cn.jzyh.service.impl;

import cn.jzyh.common.CustomException;
import cn.jzyh.entity.Category;
import cn.jzyh.entity.Dish;
import cn.jzyh.entity.Setmeal;
import cn.jzyh.mapper.CategoryMapper;
import cn.jzyh.service.CategoryService;
import cn.jzyh.service.DishService;
import cn.jzyh.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 分类管理*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;


    @Autowired
    private SetmealService setmealService;

    /*
     * 根据Id删除分类，删除之前进行判断*/
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据分类Id进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        //查询当前分类是否关联菜品，抛出业务异常
        if (count1 > 0) {
            //已关联菜品，抛出异常
            throw new CustomException("当前分类关联菜品，不允许删除");
        }

        // 如果没有关联套餐，那么不删除
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 根据分类Id进行查询
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);

        //查询当前分类是否关联套餐，抛出业务异常
        if (count2 > 0) {
            //  已关联套餐，抛出异常
            throw new CustomException("当前分类关联套餐，不允许删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
