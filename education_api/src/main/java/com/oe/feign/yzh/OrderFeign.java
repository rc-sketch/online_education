package com.oe.feign.yzh;

import com.oe.data.DataResult;
import com.oe.domain.dto.TabOrderDTO;
import com.oe.domain.vo.TabOrderVO;
import com.oe.domain.vo.TabPayLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.feign.yzh
 * @ClassName: OrderFeign
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 11:33
 * @Version: 1.0
 */
@FeignClient(value = "education-order-service")
public interface OrderFeign {
    /*订单 order */

    /***
     * 根据alipayId查询
     * @param orderId
     * @return
     */
    @GetMapping("/order/{orderId}")
    DataResult<TabOrderDTO> findOrderByOrderId(@PathVariable("orderId")Long orderId);

    /***
     * 根据alipayId查询
     * @param alipayId
     * @return
     */
    @GetMapping("/order/findOrderByAlipayId/{alipayId}")
    DataResult<List<TabOrderVO>> findOrderByAlipayId(@PathVariable("alipayId")String alipayId);

    /***
     * 根据订单id修改订单状态
     * @param orderId
     * @param status
     */
    @PutMapping("/order/{orderId}")
    DataResult updateOrderStatusByOrderId(@PathVariable("orderId")Long[] orderId,@RequestParam String status);

    /***
     * 根据订单学生名称查询
     * @param studentName
     * @return
     */
    @GetMapping("/order/findOrderByStudentName/{studentName}")
    DataResult<List<TabOrderVO>> findOrderByStudentName(@PathVariable("studentName")String studentName);

    /***
     * 增加订单
     * @param tabOrderDTO
     * @return
     */
    @PostMapping("/order/insertOrder")
    DataResult<Map<String,Object>> insertOrder(@RequestBody TabOrderDTO tabOrderDTO);


    /*支付日志 payLog*/
    /***
     * 增加日志
     * @tabTeacherVO
     * @return
     */
    @PostMapping("/pay_log/insertPayLog")
    DataResult insertPayLog(@RequestBody TabPayLogVO courseVO);

    /***
     * 根据订单讲师名称查询
     * @param teacherName
     * @return
     */
    @PostMapping("/order/findOrderByTeacherName/{teacherName}")
    //DataResult<List<TabOrderDTO>> findOrderByTeacherName(@PathVariable("teacherName")String teacherName);
    DataResult findOrderByTeacherName(@PathVariable("teacherName")String teacherName);
}
