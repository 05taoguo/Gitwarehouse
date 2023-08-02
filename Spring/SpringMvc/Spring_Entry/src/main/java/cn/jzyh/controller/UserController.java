package cn.jzyh.controller;

import cn.jzyh.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller

//定义模块名称
@RequestMapping("/user")

public class UserController {

    //请求映射路径名称
    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        System.out.println("user:save···");
        return "{'module':'user save'}";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        System.out.println("user:delete···");
        return "{'module':'user delete'}";
    }

    //普通参数:请求参数与形参相同
    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name,int age){
        System.out.println("参数传递-name：" + name);
        System.out.println("参数传统-age：" + age);
        return "{'module' : 'commonParam···'}";
    }

    //普通参数：请求参数与形参不同
    @RequestMapping("/commonParamDifferentName")
    @ResponseBody
    public String commonParamDifferentName(@RequestParam("name") String userName,int age){
        System.out.println("参数传递-name：" + userName);
        System.out.println("参数传统-age：" + age);
        return "{'module' : 'commonParamDifferentName···'}";
    }

    //POJO参数：相同
    //请求参数与形参对象中的属性对应即可完成参数传递
    @RequestMapping("/pojoParam")
    @ResponseBody
    public String pojoParam(User user){
        System.out.println("pojo参数传递-user：" + user);
        return "{'module' : 'pojoParam···'}";
    }

    //嵌套POJO参数：
    // 嵌套属性按照层次结构设定名称即可完成参数传递
    @RequestMapping("/pojoContainPojoParam")
    @ResponseBody
    public String pojoContainPojoParam(User user){
        System.out.println("pojo嵌套参数传递-user：" + user);
        return "{'module' : 'pojoContainPojo param'}";
    }

    //数组参数：
    // 同名请求参数可以直接映射到对应名称的形参数组对象中
    @RequestMapping("/arrayParam")
    @ResponseBody
    public String arrayParam(String[] likes){
        System.out.println("数组参数传递-likes：" + Arrays.toString(likes));
        return "{'module' : 'array param'}";
    }

    //集合参数：
    // 同名参数可以使用@RequestParam注解映射到对应名称的集合对象作为数据
    @RequestMapping("/listParam")
    @ResponseBody
    public String listParam(@RequestParam List<String> likes){
        System.out.println("集合参数传递-likes：" + likes);
        return "{'module' : 'list param'}";
    }

    //集合函数：json格式
    //1.开启json数据格式的自动转换，使用@EnableWenMvc
    //2.使用@RequestBody，将外部传递的json数据映射到形参的集合悐中作为数据
    @RequestMapping("/listParamForJson")
    @ResponseBody
    //声明传过来的为json数组
    public String listParamForJson(@RequestBody List<String> likes){
        System.out.println("list common(json)参数传递 list：" + likes);
        return "{'module' : 'list common for json param'}";
    }

    //POJO参数：json格式
    //1.开启json数据格式的自动转换，在配置类中开启@EnableWebMvc
    //2.使用@RequestBody注解将外部的json数组数据映射到形参的集合对象作为数据，要求属性名称一一对应
    @RequestMapping("/pojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user){
        System.out.println("pojo (json)参数传递 user：" + user);
        return "{'module' : 'pojo for json param'}";
    }

    //集合参数：json
    //1.开启json数据格式的自动转换，在配置类中开启@@EnableWebMvc
    //2.使用@RequestBody注解将外部传递到json数组数据映射到形参的保存实体类对象集合对象中，要求属性名称一一对应
    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String listPojoParamForJson(@RequestBody List<User> list){
        System.out.println("list pojo(json)参数传递 list：" + list);
        return "{'module' : 'list pojo for json param'}";
    }

    //日期参数
    //使用@DataTimeFormat注解设置日期数据类型格式，默认格式yyyy/MM/dd
    @RequestMapping("/dataParam")
    @ResponseBody
    public String dataParam(Date date,
                            @DateTimeFormat(pattern = "yyyy-MM-dd")Date date1,
                            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")Date date2){
        System.out.println("参数传递 data：" + date);
        System.out.println("参数传递 data1(yyyy-MM-dd)：" + date1);
        System.out.println("参数传递 data2(yyyy-MM-dd HH:mm:ss)：" + date2);
        return "{'module' : 'data param'}";
    }

    //响应页面/页面跳转
    @RequestMapping("/toJumHtml")
    public String toJumHtml(){
        System.out.println("html页面跳转");
        return "html/page.jsp";
    }

    //响应文本数据
    //返回String
    @RequestMapping("/ToText")
    @ResponseBody
    public String ToText(){
        System.out.println("返回文本数据类型");
        return "response text";
    }

    //响应POJO对象
    //返回User，实体类对象
    @RequestMapping("/toPOJO")
    @ResponseBody
    public User toPOJO(){
        System.out.println("返回json对象");
        User user = new User();
        user.setName("老大");
        user.setAge(18);
        return user;
    }

    //响应POJO集合对象
    //返回为集合对象，返回值为集合类型
    @RequestMapping("/toPojoList")
    @ResponseBody
    public List<User> toPojoList(){
        System.out.println("返回json集合数据");
        User user = new User();
        user.setName("老二");
        user.setAge(39);

        User user2 = new User();
        user.setName("老三");
        user.setAge(90);

        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        users.add(user2);
        return users;
    }








}
