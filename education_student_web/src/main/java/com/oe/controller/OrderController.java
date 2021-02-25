package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabOrderDTO;
import com.oe.domain.vo.TabStudentVO;
import com.oe.feign.yzh.OrderFeign;
import com.oe.pojo.TabStudent;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.controller
 * @ClassName: OrderController
 * @Author: Y眼中人Y
 * @Date: 2021/2/3 - 11:07
 * @Version: 1.0
 */
@Controller
@RequestMapping("order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private OrderFeign orderFeign;

    /***
     * 创建订单
     * @param
     * @param price
     * @return
     */
    @RequestMapping("addOrderInfo")
    @ResponseBody
    public DataResult addOrderInfo(BigDecimal price){
        try {
            // 获取认证用户
            TabStudentVO user = (TabStudentVO) SecurityUtils.getSubject().getPrincipal();
            // 容器
            TabOrderDTO item = new TabOrderDTO();
            item.setStudentName(user.getName());
            // 购买总价格
            item.setPayment(price);
            // 增加订单 返回支付需要的数据(订单名称 支付单号 总金额 描述)
            DataResult<Map<String, Object>> mapDataResult = orderFeign.insertOrder(item);
            Map<String, Object> data = mapDataResult.getData();
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(data);
        }catch (Exception e){
            logger.error("订单生成失败{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
}
