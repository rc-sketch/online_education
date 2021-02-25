package com.oe.service;

import com.oe.domain.dto.TabOrderDTO;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.service
 * @ClassName: OrderService
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:55
 * @Version: 1.0
 */
public interface OrderService {
    List<TabOrderDTO> findOrderByAlipayId(String orderId);

    /***
     * 根据订单学生名称查询
     * @param studentName
     * @return
     */
    List<TabOrderDTO> findOrderByStudentName(String studentName);

    /***
     * 根据订单id修改订单状态
     * @param orderIds
     * @param status
     */
    void updateOrderStatusByOrderId(Long[] orderIds, String status);

    TabOrderDTO findOrderById(Long orderId);

    Map<String, Object> insertOrder(TabOrderDTO tabOrderDTO);

    Map<String,Object> findOrderByTeacherName(String teacherName);
}
