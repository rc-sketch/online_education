package com.oe.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.oe.pojo.TabSeckillGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 17:14
 * @Version 1.0
 **/
@Service
public interface SeckillGoodsMapper extends BaseMapper<TabSeckillGoods> {

//    List<TabSeckillGoods> selectAll(@Param(Constants.WRAPPER) Wrapper<TabSeckillGoods> wrapper);

}
