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

    @DeleteMapping
    public R<String> deleteById(Long ids) {
        log.info("删除分类：id:{}", ids);
        System.out.println("接收到 的删除信息" + ids);

        categoryService.remove(ids);

        return R.success("分类信息删除成功");
    }


    /*
     * 根据Id修改分类信息*/
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("Id修改分类信息:{}", category);

        categoryService.updateById(category);

        return R.success("修改分类信息成功");
    }

}











