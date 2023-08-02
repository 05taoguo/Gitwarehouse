package cn.jzyh.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        System.out.println("user:save···");
        return "/user/save";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        System.out.println("user:delete···");
        return "/user/delete";
    }

}
