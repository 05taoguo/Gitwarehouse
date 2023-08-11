package cn.jzyh.service.impl;

import cn.jzyh.entity.Category;
import cn.jzyh.mapper.CategoryMapper;
import cn.jzyh.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/*
 * 分类管理*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
