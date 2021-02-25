package com.oe.service;

import com.oe.domain.dto.TabSeckillOrderDTO;
import com.oe.pojo.TabSeckillGoods;

import java.util.List;

public interface SeckillGoodsService {
    List<TabSeckillGoods> getSeckillGoodsList();

    void createOrder(Long id, String name, long l);

    TabSeckillOrderDTO selectOrderByUsername(String name);
}
