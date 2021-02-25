package com.oe.service;

import com.oe.pojo.Cart;

import java.util.List;

/**
 * @Description
 * @ClassName CartService
 * @Author RC
 * @date 2021.02.02 14:54
 */
public interface CartService {
    List<Cart> addCart(List<Cart> cartList, Long courseId);
    //从redis获取购物车数据
    List<Cart> findCartListFormRedis(String studentName);

    //从redis添加购物车数据


    List<Cart> margeCarListCookieAndRedis(List<Cart> cartListCookie, List<Cart> cartListFormRedis);

    void saveCartListToRedis(String name, List<Cart> cartList);
}
