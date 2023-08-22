package cn.jzyh.controller;

import cn.jzyh.common.R;
import cn.jzyh.dto.SetmealDto;
import cn.jzyh.entity.Category;
import cn.jzyh.entity.Setmeal;
import cn.jzyh.service.CategoryService;
import cn.jzyh.service.SetmealDishService;
import cn.jzyh.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    

    /*
    * 修改套餐信息*/
    @GetMapping("/{id}")
    public R<SetmealDto> getId(@PathVariable Long id){

        SetmealDto setmealDto = setmealService.getByIdWithFlavor(id);

        return R.success(setmealDto);
    }


    /*
    * 更新套餐-删除-添加*/
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto){

        setmealService.updateWithFlavor(setmealDto);

        return R.success("更新套餐成功");
    }


    /*
    * 更改套餐售卖状态*/
    @PostMapping("/status/{status}")
    public R<String> updateByIds(@PathVariable int status,@RequestParam List<Long> ids) {

        LambdaUpdateWrapper<Setmeal> updateWrapper = new LambdaUpdateWrapper<>();

        //设置要修改字段和值
        updateWrapper.set(Setmeal::getStatus,status);

        //设置要修改的条件
        updateWrapper.in(Setmeal::getId,ids);

        //执行更新操作
        setmealService.update(updateWrapper);

        return R.success("状态修改成功");
    }


    /*
    * 手机端用户，根据条件查询套餐数据*/
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());

        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(queryWrapper);

        return R.success(list);
    }
}
