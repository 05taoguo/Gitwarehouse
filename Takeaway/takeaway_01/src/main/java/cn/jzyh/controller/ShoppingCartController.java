package cn.jzyh.controller;

import cn.jzyh.common.BaseContext;
import cn.jzyh.common.R;
import cn.jzyh.entity.ShoppingCart;
import cn.jzyh.service.ShoppingCartService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/*
* 购物车*/
@RestController
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    /*
    * 添加菜品或者套餐到购物车*/
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){

        //设置用户id，指定用户的购物车数据
        //1.获取id
        Long currentId = BaseContext.getCurrentId();
        //2.设置到
        shoppingCart.setUserId(currentId);

        //查询当前菜品或者套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();

        //1.锁定id
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);

        //2.查询2是菜品还是套餐
        if(dishId !=null){
            //菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        }else {
            //套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }

        //是否在购物车中
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if (cartServiceOne != null ){
            //存在
            //获取数据
            Integer number = cartServiceOne.getNumber();
            //原有的基础上+1
            cartServiceOne.setNumber(number + 1);

            //更新时间
            shoppingCart.setCreateTime(LocalDateTime.now());

            //更新数据
            shoppingCartService.updateById(cartServiceOne);
        }else {
            //不存在添加到购物车
            //设置基础数量为1
            shoppingCart.setNumber(1);

            //更新时间
            shoppingCart.setCreateTime(LocalDateTime.now());
            //新增
            shoppingCartService.save(shoppingCart);

            //覆盖
            cartServiceOne = shoppingCart;;

        }

        return R.success(cartServiceOne);
    }


    /*
    * 查看购物车*/
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        log.info("购物车-：");

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();

        //根据id查询购物车的详细数据
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }


    /*
    * 减少单个菜品或者套餐的数量
    * 菜品id dishId
    * 套餐id setmealId;*/
    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){

        //设置用户id，指定用户的购物车数据
        //1.获取id
        Long currentId = BaseContext.getCurrentId();
        //2.设置到
        shoppingCart.setUserId(currentId);

        //查询当前菜品或者套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();

        //1.锁定id
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,currentId);

        //2.查询2是菜品还是套餐
        if(dishId !=null){
            //菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        }else {
            //套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }

        //是否在购物车中
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if (cartServiceOne != null ){
            //存在
            //获取数据
            Integer number = cartServiceOne.getNumber();
            //原有的基础上+1
            cartServiceOne.setNumber(number - 1);

            //更新时间
            shoppingCart.setCreateTime(LocalDateTime.now());

            //更新数据
            shoppingCartService.updateById(cartServiceOne);

            cartServiceOne = shoppingCart;;
        }

        return R.success(cartServiceOne);
    }


    /*
    * 清空购物车*/
    @DeleteMapping("/clean")
    public R<String> clean(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());

        shoppingCartService.remove(queryWrapper);

        return R.success("清空购物车");
    }
}
