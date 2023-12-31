package cn.jzyh.controller;


import cn.jzyh.common.R;
import cn.jzyh.entity.Category;
import cn.jzyh.entity.Employee;
import cn.jzyh.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 分类管理*/
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*
     * 新增分类*/
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);

        categoryService.save(category);

        return R.success("新增分类");
    }

    /*
     * 分类管理查询*/
    @GetMapping("/page")
    public R<Page<Category>> Page(@RequestParam int page, @RequestParam int pageSize) {
        Page<Category> pageData = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);

        Page<Category> paged = categoryService.page(pageData, queryWrapper);

        return R.success(paged);

    }

    /*
    * 根据Id删除分类信息*/
    @DeleteMapping
    public R<String> delete(@RequestParam("id") Long id){
        categoryService.removeById(id);
        return R.success("删除成功");
    }


    /*
     * 根据Id修改分类信息*/
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("Id修改分类信息:{}", category);

        categoryService.updateById(category);

        return R.success("修改分类信息成功");
    }


    /*
    * 根据条件查询分类*/
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        //构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        //添加排序条件
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);

        return R.success(list);
    }
}