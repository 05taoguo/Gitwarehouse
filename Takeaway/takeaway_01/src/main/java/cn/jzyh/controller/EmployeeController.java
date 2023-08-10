package cn.jzyh.controller;

import cn.jzyh.common.R;
import cn.jzyh.entity.Employee;
import cn.jzyh.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //登录功能
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        //1.将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //3.如果没有查询到则返回登录失败结果
        if(emp == null){
            return R.error("登录失败，检查用户名是否正确");
        }

        //4.密码比对，如果不一致则返回登录失败结果
        if(emp.getPassword().equals(password)){
            return R.error("登录失败，检查密码是否正确");
        }

        //5.查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(emp.getStatus() == 0){
            return R.error("登录失败，检查账号是否封禁");
        }

        //6.登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    //退出功能
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");;
        return R.success("退出成功...");
    }

    //新增员工
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息:{}",employee.toString());
        //1:密码初始并且加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

/*        //2:设置更新时间和创建时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //3：设置更新人和创建人
        Long empId = (Long)request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);*/

        //4：执行保存
        employeeService.save(employee);

        return R.success("保存成功");
    }

    //分页查询
    @GetMapping("/page")
    public R<Page<Employee>> page(int page, int pageSize, String name){
        log. info("page = {}, pageSize = {}, name = {}",page,pageSize,name );

        //1.创建page对象
        Page<Employee> pageInfo = new Page<>(page, pageSize);

        //2:构建条件查询
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);

        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //3:查询
        employeeService.page(pageInfo,queryWrapper);
        
        return R.success(pageInfo);
    }
    

    /*
    * 修改账号状态
    * */
    @PutMapping
    public R<String> updates(HttpServletRequest request, @RequestBody Employee employee){


        long id = Thread.currentThread().getId();
        log.info("线程id：{}",id);

/*        Long employeeId = (Long) request.getSession().getAttribute("employee");
        //修改用户
        employee.setUpdateUser(employeeId);
        //修改时间
        employee.setUpdateTime(LocalDateTime.now());*/

        //更新
        employeeService.updateById(employee);

        return R.success("更新成功");

    }


   /*
   * 根据id修改员工信息
   * */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);

        if (employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应的员工信息");
    }
}