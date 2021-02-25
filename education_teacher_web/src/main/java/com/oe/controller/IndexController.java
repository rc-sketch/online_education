package com.oe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-30 17:19
 **/
@Controller
public class IndexController {
    // 跳转到登录页面
    @RequestMapping({"toLogin","/"})
    public String toLogin(){
        return "shoplogin";
    }
    //跳转到注册页面
    @RequestMapping("toRegister")
    public String toRegister(){
        return "register";
    }
    //跳转到首页
    @RequestMapping("toIndex")
    public String toIndex(){
        return "admin/index";
    }
    //跳转到新增课程信息页面
    @RequestMapping("course_edit")
    public String goods_edit(){
        return "admin/course_edit";
    }
    // 跳转到课程目录页面
    @RequestMapping("course")
    public String course(){
        return "admin/course";
    }
    // 跳转到课程考试页面
    @RequestMapping("goods")
    public String goods(){
        return "admin/goods";
    }
    //销售课程
    @RequestMapping("courseshop")
    public String courseshop(){
        return "admin/courseshop";
    }
    //收到的礼物
    @RequestMapping("teacher_gift")
    public String teacher_gift(){
        return "admin/teacher_gift";
    }
    //提现记录
    @RequestMapping("cash_log")
    public String cash_log(){
        return "admin/cash_log";
    }
    //学员列表
    @RequestMapping("student_list")
    public String student_list(){
        return "admin/student_list";
    }
    //基本信息
    @RequestMapping("seller")
    public String seller(){
        return "admin/seller";
    }
    //基本信息
    @RequestMapping("password")
    public String password(){
        return "admin/password";
    }
    //家目录
    @RequestMapping("home")
    public String home(){
        return "admin/home";
    }
    //家目录
    @RequestMapping("toAdd")
    public String toAdd(){
        return "menu/add";
    }

}

    