package cn.jzyh.controller;

import cn.jzyh.common.R;
import cn.jzyh.entity.User;
import cn.jzyh.service.UserService;
import cn.jzyh.util.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /*
    * 发送手机验证码*/
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机号
        String phone = user.getPhone();

        if(StringUtils.isNotEmpty(phone)){
            //生成验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("验证码code={}",code);

            //发送验证码
            //SMSUtils.sendMessage("名称","模板code",phone,code);

            session.setAttribute(phone,code);

            return R.success("手机验证码发送成功");

        }

        return R.success("手机验证码发送失败");
    }


    /*
    * 移动端用户登录*/
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){

        //map获取手机号与验证码
        String phone = map.get("phone").toString();

        String code = map.get("code").toString();

        //从session获取保存的验证码
        Object codeInSession = session.getAttribute(phone);

        //验证码的比对，提交的与保存的验证码比对
        if(codeInSession != null && codeInSession.equals(code)){
            //比对正确，登录成功

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

            //获取手机号
            queryWrapper.eq(User::getPhone,phone);

            //唯一标识
            User user = userService.getOne(queryWrapper);

            //判断手机号是否是新用户，如果是自动注册保存
            if(user == null){
                user = new User();

                //手机号
                user.setPhone(phone);

                //状态，正常使用 ，初始值1
                user.setStatus(1);

                //注册
                userService.save(user);
            }

            //登录成功,给session保存一份
            session.setAttribute("user",user.getId());

            return R.success(user);
        }

        return R.error("登录失败");
    }
}
