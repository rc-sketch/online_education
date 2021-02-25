package com.oe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springcloud
 * @description:
 * @author: 邢孟君
 * @create: 2021-02-01 23:37
 **/
@Controller
public class IndexController {

    /*跳转首页展示*/
    @RequestMapping({"/","index"})
    public String toIndex(){
        return "index";
    }

    /*跳转登录*/
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    /*跳转注册*/
    @RequestMapping("register")
    public String toRegister(){
        return "register";
    }

    /*结算页面*/
    @RequestMapping("getOrderInfo")
    public String getOrderInfo(){
        return "getOrderInfo";
    }

    /*结算页面*/
    @RequestMapping("cart")
    public String cart(){
        return "cart";
    }

    /*跳转search页面*/
    @RequestMapping("search")
    public String search(){
        return "search";
    }

    /*跳到秒杀页面*/
    @RequestMapping("seckill")
    public String seckill(){
        return "seckill-index";
    }



}