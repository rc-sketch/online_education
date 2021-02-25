package com.oe.feign.sh;

import com.oe.data.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ProjectName: online_education
 * @Package: com.oe.feign.sh
 * @ClassName: SeckillFeign  秒杀
 * @Author: 石晗
 * @Date: 2021/2/2 - 08:50
 * @Version: 1.0
 */
@FeignClient(value = "education-seckill-service")
public interface SeckillFeign {
    /***
     * 查询全部秒杀商品
     * @return
     */
    @GetMapping("getSeckillGoodsList")
    DataResult getSeckillGoodsList();

    /***
     * 增加订单信息
     * @param id        秒杀商品的id
     * @param name      秒杀商品的name
     * @return
     */
    @PostMapping("createOrder/{id}/{name}")
    DataResult createOrder(@PathVariable("id") Long id,@PathVariable("name")String name);

    /***
     * 根据id查询秒杀商品
     * @param id        秒杀商品的id
     * @return          seckillGoods
     */
    @GetMapping("selectSeckillGoodsById/{id}")
    DataResult selectSeckillGoodsById(@PathVariable("id") Long id);

    /***
     * 根据id查询秒杀商品
     * @param name          登录用户的的name
     * @return              seckillOrder
     */
    @GetMapping("findOrder/{name}")
    DataResult findOrder(@PathVariable("name") String name);
}
