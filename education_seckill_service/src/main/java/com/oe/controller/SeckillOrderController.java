package com.oe.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.oe.constant.SeckillRedis;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabSeckillGoodsDTO;
import com.oe.domain.dto.TabSeckillOrderDTO;
import com.oe.domain.vo.AlipayVO;
import com.oe.pojo.TabSeckillGoods;
import com.oe.pojo.TabStudent;
import com.oe.service.SeckillGoodsService;
import com.oe.util.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 16:39
 * @Version 1.0
 **/
@RestController
@Api(value = "秒杀接口")
@RefreshScope
public class SeckillOrderController {

    private static final Logger logger = LoggerFactory.getLogger(SeckillOrderController.class);

    @Resource
    private SeckillGoodsService seckillGoodsService;
    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "查询全部秒杀商品")//定义方法名称
    @GetMapping("getSeckillGoodsList")
    public DataResult getSeckillGoodsList(){
        try{
            logger.debug("进入>>>>>getSeckillGoodsList");
            List<TabSeckillGoods> seckillGoodsList = seckillGoodsService.getSeckillGoodsList();
            logger.debug("结束>>>>>getSeckillGoodsList,返回list参数: {}",seckillGoodsList);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(seckillGoodsList);
        }catch (Exception e){
            logger.error("getSeckillGoodsList",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiImplicitParam(value = "秒杀商品的id",name = "id",required = true,paramType = "path")
    @ApiOperation(value = "根据id查询秒杀商品")//定义方法名称
    @GetMapping("selectSeckillGoodsById/{id}")
    public DataResult selectSeckillGoodsById(@PathVariable("id") Long id) {
        try{
            logger.debug("进入>>>>>selectSeckillGoodsById,参数为 {}",id);
            TabSeckillGoodsDTO seckillGoods = (TabSeckillGoodsDTO)redisTemplate.boundHashOps(SeckillRedis.KEY_SECKILLGOODS).get(id);
            logger.debug("结束>>>>>selectSeckillGoodsById,返回list参数: {}",seckillGoods);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST).setData(seckillGoods);
        }catch (Exception e){
            logger.error("createOrder",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST).setData("查询失败");
        }
    }


    @ApiOperation(value = "增加订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "秒杀商品的id",name = "id",required = true,paramType = "path"),
            @ApiImplicitParam(value = "秒杀商品的name", name = "name", required = true, paramType = "path"),
    })
    @PostMapping("createOrder/{id}/{name}")
    public DataResult createOrder(@PathVariable("id") Long id,@PathVariable("name")String name){//id是秒杀商品的id
        logger.debug("进入>>>>>createOrder,参数为 {},{}",id,name);
//        //判断用户是否登陆
//        TabStudent student = (TabStudent) SecurityUtils.getSubject().getPrincipal();
//        if (student == null){
//            return DataResult.response(ResponseStatusEnum.UNLOGIN);
//        }

        AlipayVO alipay = new AlipayVO();
        IdWorker idWorker = new IdWorker();
        long l = idWorker.nextId();
        alipay.setOutTradeNo(valueOf(l));
        //hutool生成随机订单号
        String format = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String numbers = RandomUtil.randomNumbers(5);
        alipay.setSubject(format + numbers);
        alipay.setBody("seckill");

        try{
            seckillGoodsService.createOrder(id,name,l);
        }catch (Exception re){
            logger.error("createOrder",re);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST).setData("商品已经没有了");
        }
        logger.debug("结束>>>>>createOrder,返回list参数: {}",alipay);
        return DataResult.response(ResponseStatusEnum.SUCCESS).setData(alipay);
    }

    @ApiImplicitParam(value = "登录用户name",name = "name",required = true,paramType = "path")
    @ApiOperation(value = "根据登录用户查询秒杀订单")//定义方法名称
    @GetMapping("findOrder/{name}")
    public DataResult findOrder(@PathVariable("name") String name){
        TabSeckillOrderDTO seckillOrder = seckillGoodsService.selectOrderByUsername(name);
        if (seckillOrder == null){
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR).setData("没货");
        }else if (seckillOrder.getId().intValue() == -999){
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
        return DataResult.response(ResponseStatusEnum.SUCCESS).setData(seckillOrder);
    }

}
