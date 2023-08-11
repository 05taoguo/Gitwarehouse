package cn.jzyh.service.impl;

import cn.jzyh.entity.Employee;
import cn.jzyh.mapper.EmployeeMapper;
import cn.jzyh.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/*
 * 员工管理*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
