package cn.jzyh.service.impl;

import cn.jzyh.entity.User;
import cn.jzyh.mapper.UserMapper;
import cn.jzyh.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
