package com.oe.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabStudentVO;
import com.oe.pojo.Cart;
import com.oe.pojo.TabStudent;
import com.oe.service.CartService;
import com.oe.util.CookieUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description
 * @ClassName CartController
 * @Author RC
 * @date 2021.02.02 14:58
 */
@RestController
@RequestMapping("cart")
@RefreshScope//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "购物车接口")
public class CartController {

    @Autowired
    private CartService cartService;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CartController.class);

    //查询购物车
    @PostMapping("cartList")
    @ApiOperation(value = "查询购物车信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "学员对象",
                    paramType = "body" ,dataTypeClass = TabStudentVO.class),
    })
    public String cartList(HttpServletRequest request, HttpServletResponse response,@RequestBody TabStudentVO user){
        //获取用户主体 判断是否登录
        //从cookie中查询,cookie中保存的所有的数据都是字符串,如果是对象也是json字符串
        String cartList = CookieUtils.getCookieValue(request, "cartList", "utf-8");
        //如果用户第一次操作cookie是空的,或者是""字符串
        if(StrUtil.hasBlank(cartList) || cartList.equals("")){
            cartList = "[]";
        }
        //使用fastJson将字符串carList转成list
        List<Cart> cartListCookie = JSONObject.parseArray(cartList, Cart.class);
        
        if(user == null){//未登录查询cookie
            //return cartListCookie;
            return "";
        }else {//如果登录了将cookie和redis合并
            //从redis中获取
            List<Cart> cartListFormRedis = cartService.findCartListFormRedis(user.getName());
            //如果cartListCookie有数据在合并
            if (cartListCookie.size() > 0) {
                //合并cookie
                List<Cart> margeCarList = cartService.margeCarListCookieAndRedis(cartListCookie, cartListFormRedis);
                //将合并好的购物车,保存在redis
                cartService.saveCartListToRedis(user.getName(), margeCarList);
                //合并购物车之后删除cookie
                CookieUtils.deleteCookie(request, response, "cartList");
                return "";//合并后数据
            }
            String jsonString = JSONObject.toJSONString(cartListFormRedis);
            return jsonString;
        }
    }
    //@PostMapping("addCart")
   @PostMapping("addCart/{courseId}")
   @ApiOperation(value = "根据主键增加购物车信息")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "courseId", value = "课程id", required = true,
                   paramType = "path", dataTypeClass = Long.class),
   })
    public DataResult addCart(HttpServletRequest request, HttpServletResponse response,@PathVariable("courseId") Long courseId,@RequestBody TabStudentVO user){
        //添加之前先查询购物车
       String cartList1 = cartList(request,response,user);
       List<Cart> cartList = JSONObject.parseArray(cartList1, Cart.class);
       //调用carService拼接购物车
        cartList = cartService.addCart(cartList,courseId);
        //获取用户主体 判断是否登录
        if(user == null){
            CookieUtils.setCookie(request,response,"cartList", JSONObject.toJSONString(cartList),24*60*60,"utf-8");
        }else {
            //登录了将购物车信息放入redis
            cartService.saveCartListToRedis(user.getName(), cartList);
        }

       return DataResult.response(ResponseStatusEnum.SUCCESS);
    }
}
