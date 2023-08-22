package cn.jzyh.service.impl;

import cn.jzyh.entity.OrderDetail;
import cn.jzyh.mapper.OrderDetailMapper;
import cn.jzyh.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
