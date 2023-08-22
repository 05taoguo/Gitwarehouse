package cn.jzyh.service;

import cn.jzyh.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrdersService extends IService<Orders> {

    /*
    * 用户下单*/
    void submit(Orders orders);
}
