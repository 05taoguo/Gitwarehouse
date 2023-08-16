package cn.jzyh.dto;

import cn.jzyh.entity.Setmeal;
import cn.jzyh.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}