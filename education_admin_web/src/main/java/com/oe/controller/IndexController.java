package com.oe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @ClassName IndexController
 * @Author RC
 * @date 2021.01.30 16:15
 */
@Controller
public class IndexController {

    @RequestMapping({"index","/"})
    public String index(){
        return "login";
    }
    @RequestMapping("toIndex")
    public String toIndex(){
        return "admin/index";
    }

    @RequestMapping("home")
    public String home(){
        return "admin/home";
    }

    @RequestMapping("teacher")
    public String teacher(){
        return "admin/teacher";
    }

    @RequestMapping("gift")
    public String gift(){
        return "admin/gift";
    }

    @RequestMapping("course")
    public String course(){
        return "admin/course";
    }

    @RequestMapping("course_tag")
    public String courseTag(){
        return "admin/course_tag";
    }

    @RequestMapping("item_cat")
    public String itemCat(){
        return "admin/item_cat";
    }

    @RequestMapping("seckill_goods")
    public String seckillGoods(){
        return "admin/seckill_goods";
    }

    @RequestMapping("student")
    public String student(){
        return "admin/student";
    }

    @RequestMapping("score")
    public String score(){
        return "admin/score";
    }

    @RequestMapping("content_category")
    public String contentCategory(){
        return "admin/content_category";
    }

    @RequestMapping("content")
    public String content(){
        return "admin/content";
    }

    @RequestMapping("course_section")
    public String courseSection(){
        return "admin/course_section";
    }



}
