package com.oe.controller;

import com.alibaba.fastjson.JSONObject;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabStudentVO;
import com.oe.feign.rc.CartFeign;

import com.oe.pojo.Cart;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Description
 * @ClassName CartController
 * @Author RC
 * @date 2021.02.02 18:00
 */
@Controller
@RequestMapping("cartController")
@RefreshScope
public class CartController {

    @Autowired
    private CartFeign cartFeign;
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @RequestMapping("addCart/{courseId}")
    public String  addCart(@PathVariable("courseId") Long courseId){
        try {
            logger.info("根据课程id 增加购物车信息");
            //获取用户主体 判断是否登录
            Subject subject = SecurityUtils.getSubject();
            TabStudentVO user =(TabStudentVO) subject.getPrincipal();
            //调用购物车服务接口
            DataResult dataResult = cartFeign.addCart(courseId,user);
        } catch (Exception e) {
            logger.error("购物车接口 增加购物车信息异常 :{}",e);
        }
        return "success-cart";
    }

    @PostMapping("cartList")
    @ResponseBody
    public DataResult  cartList(){
        try {
            //获取用户主体 判断是否登录
            Subject subject = SecurityUtils.getSubject();
            TabStudentVO user =(TabStudentVO) subject.getPrincipal();
            logger.info("查询购物车信息");
            String cartString = cartFeign.cartList(user);
            List<Cart> cartList = JSONObject.parseArray(cartString, Cart.class);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(cartList);
        } catch (Exception e) {
            logger.error("购物车接口 查询购物车信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
