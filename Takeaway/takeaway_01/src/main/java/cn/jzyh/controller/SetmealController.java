package cn.jzyh.controller;

import cn.jzyh.common.R;
import cn.jzyh.dto.SetmealDto;
import cn.jzyh.entity.Category;
import cn.jzyh.entity.Setmeal;
import cn.jzyh.service.CategoryService;
import cn.jzyh.service.SetmealDishService;
import cn.jzyh.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
* 套餐管理*/
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;


    /*
    * 新增套餐*/
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);

        setmealService.saveWitDish(setmealDto);

        return R.success("新增成功");
    }


    /*
    * 套餐分页查询*/
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //分页构造器
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();

        //模糊查询
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Setmeal::getName,name);

        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        //查询条件
        setmealService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,setmealDtoPage,"records");

        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list = records.stream().map((item) ->{
            SetmealDto setmealDto = new SetmealDto();

            //对象拷贝
            BeanUtils.copyProperties(item,setmealDto);

            //分类id
            Long categoryId = item.getCategoryId();

            //根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);

            if (category != null){
                //替换分类名称
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }

            return setmealDto;

        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(list);
        return R.success(setmealDtoPage);
    }


    /*
    * 删除套餐*/
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){

        setmealService.removeWitDish(ids);

        return R.success("套餐删除成功");
    }
}
