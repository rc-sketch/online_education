package com.oe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oe.constant.SeckillRedis;
import com.oe.domain.dto.TabSeckillOrderDTO;
import com.oe.mapper.SeckillGoodsMapper;
import com.oe.pojo.TabSeckillGoods;
import com.oe.service.SeckillGoodsService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 16:53
 * @Version 1.0
 **/
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Scheduled(cron = "0/1 * * * * ?")
    public void doJobGetSeckillGoodsList(){

        QueryWrapper<TabSeckillGoods> queryWrapper = new QueryWrapper<>();
        Date now = new Date();
        queryWrapper.eq("status","1").lt("start_time",now).gt("end_time",now);
        List<TabSeckillGoods> seckillGoods = seckillGoodsMapper.selectList(queryWrapper);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //将每条数据存入缓存
        for (TabSeckillGoods seckillGood : seckillGoods) {
            redisTemplate.boundHashOps(SeckillRedis.KEY_SECKILLGOODS).put(seckillGood.getId(),seckillGood);
            //查询时需要我们将预计减库存放入redis中,        先不做库存
//            redisTemplate.boundValueOps(SeckillRedis.KEY_SECKILLGOODS_STOCKCOUNT + seckillGood.getId()).set(seckillGood.getStockCount().toString());
        }
    }

    @Override
    public List<TabSeckillGoods> getSeckillGoodsList() {
        List<TabSeckillGoods> seckillGoods = redisTemplate.boundHashOps(SeckillRedis.KEY_SECKILLGOODS).values();
        return seckillGoods;
    }

    @Override
    public void createOrder(Long id, String name, long l) {
        //发送mq异步消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentName",name);
        jsonObject.put("seckillId",id);
        jsonObject.put("transactionId",valueOf(l));
        String jsonString = jsonObject.toJSONString();
        rocketMQTemplate.convertAndSend(SeckillRedis.KEY_SECKILLGOODS,jsonString);
    }

    @Override
    public TabSeckillOrderDTO selectOrderByUsername(String name) {
        TabSeckillOrderDTO seckillOrder = (TabSeckillOrderDTO)redisTemplate.boundHashOps(SeckillRedis.KEY_SECKILL_ORDER).get(name);
        return seckillOrder;
    }
}
