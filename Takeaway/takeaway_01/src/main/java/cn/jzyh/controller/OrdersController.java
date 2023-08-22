package cn.jzyh.controller;

import cn.jzyh.common.R;
import cn.jzyh.entity.Orders;
import cn.jzyh.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){

        ordersService.submit(orders);

        return R.success("下单成功");
    }

}
