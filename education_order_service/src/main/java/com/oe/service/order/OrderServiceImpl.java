package com.oe.service.order;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oe.config.IdWorker;
import com.oe.domain.dto.TabOrderDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.mapper.order.OrderMapper;
import com.oe.pojo.Cart;
import com.oe.pojo.TabOrder;
import com.oe.service.OrderService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.service.order
 * @ClassName: OrderServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:55
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private IdWorker idWorker;

    @Resource
    private RedisTemplate redisTemplate;

    /***
     * 根据订单id查询
     * @param orderId
     * @return
     */
    @Override
    public TabOrderDTO findOrderById(Long orderId) {
        TabOrder order = orderMapper.selectById(orderId);
        TabOrderDTO dto = new TabOrderDTO();
        BeanUtils.copyProperties(order,dto);
        return dto;
    }

    /***
     * 根据订单id修改订单状态
     * @param orderIds
     * @param status
     */
    @Override
    public void updateOrderStatusByOrderId(Long[] orderIds, String status) {
        for (int i = 0; i < orderIds.length; i++) {
            TabOrder order = new TabOrder();
            order.setOrderId(orderIds[i]);
            order.setStatus(status);
            orderMapper.updateById(order);
        }
    }

    /***
     * 根据订单学生名称查询
     * @param studentName
     * @return
     */
    @Override
    public List<TabOrderDTO> findOrderByStudentName(String studentName) {
        Map map = new HashMap();
        map.put("student_name",studentName);
        List<TabOrder> orderList = orderMapper.selectByMap(map);
        List<TabOrderDTO> dtoList = new ArrayList<>();
        orderList.forEach(order -> {
            TabOrderDTO dto = new TabOrderDTO();
            BeanUtils.copyProperties(order,dto);
            dtoList.add(dto);
        });
        return dtoList;
    }

    /***
     * 根据aliPayId查询
     * @param alipayId
     * @return
     */
    @Override
    public List<TabOrderDTO> findOrderByAlipayId(String alipayId) {
        Map map = new HashMap();
        map.put("alipay_id",alipayId);
        List<TabOrder> orderList = orderMapper.selectByMap(map);
        List<TabOrderDTO> dtoList = new ArrayList<>();
        orderList.forEach(order -> {
            TabOrderDTO dto = new TabOrderDTO();
            BeanUtils.copyProperties(order,dto);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public Map<String, Object> insertOrder(TabOrderDTO tabOrderDTO) {
        // 获取总价格 收货人地址id 认证用户
        BigDecimal price = tabOrderDTO.getPayment();
        String username = tabOrderDTO.getStudentName();
        // 获取认证用户

        // 获取需要加入订单的商品
        Object str = redisTemplate.opsForHash().get("cartList", username);
        String jsonString = JSONObject.toJSONString(str);
        List<Cart> carts1 = JSONObject.parseArray(jsonString, Cart.class);
        // 订单id
        Long tradeNo = idWorker.nextId();
        long orderId = idWorker.nextId();
        for (int i1 = 0; i1 < carts1.size(); i1++) {
            // 创建支付单号
            List<TabCourseVO> courseVOList = carts1.get(i1).getCourseVOList();
            for (int i = 0; i < courseVOList.size(); i++) {
                TabOrderDTO order = new TabOrderDTO();
                order.setAlipayId(tradeNo+"");
                // 商家
                order.setTeacherName(courseVOList.get(i).getTeacherName());
                // 收货人名称
                order.setReceiver(carts1.get(i).getName());
                order.setOrderId(orderId);
                // 价格
                order.setPayment(price);
                order.setPostFee(price.toString());
                // 订单创建时间
                order.setCreateTime(new Date());
                long orderItemId = idWorker.nextId();
                // 增加订单
                // 物流单号
                order.setShippingCode(orderItemId + "");
                order.setStatus("1");
                TabOrder tabOrder = new TabOrder();
                BeanUtils.copyProperties(order,tabOrder);
                orderMapper.insert(tabOrder);
            }
        }

        // 删除缓存
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete("cartList",username);
        Map<String,Object> map = new HashMap<>();
        // 支付订单
        map.put("out_trade_no",tradeNo);
        // 描述
        map.put("body","用户:" + username + "在" + new Date() + "创建了订单");
        // 付款金额
        map.put("total_amount",price);
        // 订单名称
        map.put("subject",username + orderId);
        // 用户名称
        map.put("username",username);
        return map;
    }

    /***
     * 查询当前讲师课程销量统计按月统计
     * @param teacherName
     * @return
     */
    @Override
    public Map<String,Object> findOrderByTeacherName(String teacherName) {
        Map map = new HashMap();
        map.put("teacher_name",teacherName);
        List<TabOrder> orderList = orderMapper.selectByMap(map);

        List<TabOrderDTO> dtoList = new ArrayList<>();
        orderList.forEach(order -> {
            TabOrderDTO dto = new TabOrderDTO();
            BeanUtils.copyProperties(order,dto);
            dtoList.add(dto);
        });

        Map<String,Object> map1 = new HashMap();
        List<String> feeList = new ArrayList<>();
        List tList = new ArrayList<>();
        List hList = new ArrayList<>();
        //去年
        int[] lastYear = {22,33,44,55,88,99,11,222,11,99,100,200};
        DecimalFormat df = new DecimalFormat();
        //金钱
        for (TabOrderDTO tabOrderDTO : dtoList) {
            feeList.add((tabOrderDTO.getPostFee()));
        }
        //同比统计  今年 - 去年 / 去年

        for (int i = 0; i < feeList.size(); i++) {
            //double tb = (feeList.get(i) - lastYear[i]) / lastYear[i] ;
            String tb = df.format((int)((Integer.parseInt(feeList.get(i)) - lastYear[i]) * 100) /  lastYear[i]);
            tList.add(tb);
        }
        //环比计算 当月 - 上月 / 上月
        for (int i = 0; i < feeList.size(); i++) {
            if(i != 0){
                String hb = df.format((double) (Integer.parseInt(feeList.get(i)) - Integer.parseInt(feeList.get(i-1)) *100) / Integer.parseInt(feeList.get(i-1)));
                hList.add(hb);
            }else {
                double hb= 0;
                hList.add(hb);
            }
        }
        map1.put("fee",feeList);
        map1.put("tb",tList);
        map1.put("hb",hList);
        return map1;
    }
}
