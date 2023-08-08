package cn.jzyh.service.impl;

import cn.jzyh.domain.User;
import cn.jzyh.dao.UserDao;
import cn.jzyh.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoTao
 * @since 2023-08-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
