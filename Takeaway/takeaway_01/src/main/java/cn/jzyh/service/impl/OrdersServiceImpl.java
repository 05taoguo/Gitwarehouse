package cn.jzyh.service.impl;

import cn.jzyh.common.BaseContext;
import cn.jzyh.common.CustomException;
import cn.jzyh.entity.*;
import cn.jzyh.mapper.OrdersMapper;
import cn.jzyh.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 提交订单
     */
    @Override
    @Transactional
    public void submit(Orders orders) {

        //1:获取当前登录用户id
        Long userId = BaseContext.getCurrentId();

        //2:获取用户信息
        User user = userService.getById(userId);

        //3:获取用户的默认地址
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null) {
            throw new CustomException("请确认你的默认地址，是否正确！");
        }

        //4:获取购物车的菜品或者套餐
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(ShoppingCart::getUserId, userId);

        List<ShoppingCart> shoppingCarts = shoppingCartService.list(queryWrapper);
        if (CollectionUtils.isEmpty(shoppingCarts)) {
            throw new CustomException("购物车没有菜品或者套餐，请确认！");
        }

        //累加购物车中的菜品-套餐的价格
        AtomicInteger totalAmount = new AtomicInteger(0);

        //订单号
        long orderId = IdWorker.getId();

        //5:组装成订单明细信息 ==> 每一个菜品或者套餐对应一条订单明细
        List<OrderDetail> orderDetails = shoppingCarts.stream().map(shoppingCart -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(shoppingCart.getNumber());
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
            orderDetail.setDishId(shoppingCart.getDishId());
            orderDetail.setSetmealId(shoppingCart.getSetmealId());
            orderDetail.setName(shoppingCart.getName());
            orderDetail.setImage(shoppingCart.getImage());
            orderDetail.setAmount(shoppingCart.getAmount());
            //totalAmount = totalAmount + shoppingCart.getAmount():
            totalAmount.addAndGet(shoppingCart.getAmount().multiply(new BigDecimal(shoppingCart.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        //5: 组装订单信息

        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());

        //订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(totalAmount.get()));//总金额
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));


        //7: 把订单信息保存到订单表
        this.save(orders);

        //8:订单明细信息保存到订单明细表
        orderDetailService.saveBatch(orderDetails);

        //9:清除购物车数据
        shoppingCartService.remove(queryWrapper);
    }
}
