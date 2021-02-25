package com.oe.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabSeckillGoodsDTO;
import com.oe.domain.dto.TabSeckillOrderDTO;
import com.oe.feign.sh.SeckillFeign;
import com.oe.pojo.TabStudent;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/3 18:48
 * @Version 1.0
 **/
@Controller
@RequestMapping("seckillOrderController")
public class SeckillOrderController {

    private static final Logger logger = LoggerFactory.getLogger(SeckillOrderController.class);

    @Resource
    private SeckillFeign seckillFeign;

    @RequestMapping("getSeckillGoodsList")
    @ResponseBody
    public DataResult getSeckillGoodsList(){
        try{
            DataResult seckillGoodsList = seckillFeign.getSeckillGoodsList();
            return seckillGoodsList;
        }catch (Exception e){
            logger.error("getSeckillGoodsList",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("selectSeckillGoodsById")
    public String selectSeckillGoodsById(Long id, Model model){
        DataResult seckillGoodsData = seckillFeign.selectSeckillGoodsById(id);
        TabSeckillGoodsDTO seckillGoods = (TabSeckillGoodsDTO)seckillGoodsData.getData();
        if (seckillGoods == null){
            return "noGoods";
        }
        model.addAttribute("seckillGoods",seckillGoods);
        return "seckill-item";
    }

    @RequestMapping("createOrder")
    @ResponseBody
    public DataResult createOrder(Long id){//id是秒杀商品的id

        //判断用户是否登陆
        TabStudent user = (TabStudent) SecurityUtils.getSubject().getPrincipal();
        if (user == null){
            return DataResult.response(ResponseStatusEnum.UNLOGIN);
        }
        try{
            DataResult order = seckillFeign.createOrder(id, user.getName());
            return order;
        }catch (RuntimeException re){
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("findOrder")
    @ResponseBody
    public DataResult findOrder(){//id是秒杀商品的id
        //判断用户是否登陆
        TabStudent user = (TabStudent) SecurityUtils.getSubject().getPrincipal();
        DataResult order = seckillFeign.findOrder(user.getName());
//        TabSeckillOrderDTO seckillOrder = (TabSeckillOrderDTO)order.getData();
        return order;

    }
}
