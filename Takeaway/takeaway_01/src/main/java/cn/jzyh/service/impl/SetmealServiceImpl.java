package cn.jzyh.service.impl;

import cn.jzyh.common.CustomException;
import cn.jzyh.dto.SetmealDto;
import cn.jzyh.entity.Setmeal;
import cn.jzyh.entity.SetmealDish;
import cn.jzyh.mapper.SetmealMapper;
import cn.jzyh.service.SetmealDishService;
import cn.jzyh.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /*
     * 新增套餐，同时保存套餐和菜品的关联*/
    @Override
    @Transactional
    public void saveWitDish(SetmealDto setmealDto) {
        //保存套餐基本信息，setmeal执行insert
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getCategoryId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }


    /*
     * 删除套餐,删除套餐和菜品的关联数据 */
    @Override
    public void removeWitDish(List<Long> ids) {

        //查询套餐状态是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        int count = this.count(queryWrapper);

        //无法删除抛出异常
        if (count > 0) {
            throw new CustomException("套餐售卖中，禁止删除");
        }

        //可以删除，先删除套餐表数据,setmeal
        this.removeByIds(ids);

        //删除关系表中的数据,setmeal_dish
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);

        setmealDishService.remove(lambdaQueryWrapper);

    }


    /*
     * 根据id查询套餐信息*/
    @Override
    public SetmealDto getByIdWithFlavor(Long id) {

        //根据id查询数据 ，setmeal
        Setmeal setmeal = this.getById(id);

        SetmealDto setmealDto = new SetmealDto();

        //拷贝
        BeanUtils.copyProperties(setmeal,setmealDto);

        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getId, setmeal.getId());

        List<SetmealDish> setmealDishes = setmealDishService.list(queryWrapper);

        setmealDto.setSetmealDishes(setmealDishes);

        return setmealDto;
    }


    /*
     * 更新套餐数据-删除-添加*/
    @Override
    public void updateWithFlavor(SetmealDto setmealDto) {
        //更新数据
        this.updateById(setmealDto);
        
        //对应的套餐菜品
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getId,setmealDto.getId());

        //删除已有的数据
        setmealDishService.remove(queryWrapper);

        //添加新的数据
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        setmealDishes = setmealDishes.stream().map((item) ->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);

        //
    }
}