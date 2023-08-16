package cn.jzyh.service;

import cn.jzyh.dto.SetmealDto;
import cn.jzyh.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 套餐
 */
public interface SetmealService extends IService<Setmeal> {

    /*
     * 新增套餐，同时保存套餐和菜品的关联*/
    void saveWitDish(SetmealDto setmealDto);

    /*
    * 删除套餐,删除套餐和菜品的关联数据 */
    void removeWitDish(List<Long> ids);
}
