package com.oe.controller;

import com.alibaba.fastjson.JSONObject;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabOrderDTO;
import com.oe.domain.vo.TabOrderVO;
import com.oe.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.controller
 * @ClassName: OrderController
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:53
 * @Version: 1.0
 */
@RestController
@RequestMapping("order")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "订单接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    /***
     * 根据alipayId查询
     * @param orderId
     * @return
     */
    @GetMapping("{orderId}")
    @ApiOperation(value = "根据订单Id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "alipayId", value = "订单ID", required = true,
                    paramType = "path", dataTypeClass = Long.class),
    })
    public DataResult<TabOrderDTO> findOrderByOrderId(@PathVariable("orderId")Long orderId){
        try {
            TabOrderDTO dto = orderService.findOrderById(orderId);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(dto);
        }catch (Exception e){
            logger.error("订单接口  根据订单Id查询异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据alipayId查询
     * @param alipayId
     * @return
     */
    @GetMapping("/findOrderByAlipayId/{alipayId}")
    @ApiOperation(value = "根据订单alipayId查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "alipayId", value = "订单父ID", required = true,
                    paramType = "path", dataTypeClass = String.class),
    })
    public DataResult<List<TabOrderVO>> findOrderByAlipayId(@PathVariable("alipayId")String alipayId){
        try {
            List<TabOrderDTO> dtoList = orderService.findOrderByAlipayId(alipayId);
            List<TabOrderVO> voList = new ArrayList<>();
            dtoList.forEach(dto -> {
                TabOrderVO vo = new TabOrderVO();
                BeanUtils.copyProperties(dto,vo);
                voList.add(vo);
            });
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(voList);
        }catch (Exception e){
            logger.error("订单接口  根据订单i查询异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据订单id修改订单状态
     * @param orderIds
     * @param status
     */
    @PutMapping("/{orderIds}")
    @ApiOperation(value = "根据订单id修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderIds", value = "订单id", required = true,
                    paramType = "path", dataTypeClass = Long[].class),
            @ApiImplicitParam(name = "status", value = "订单状态", required = true,
                    dataTypeClass = String.class),
    })
    public DataResult updateOrderStatusByOrderId(@PathVariable("orderIds")Long[] orderIds,@RequestParam String status){
        try {
            orderService.updateOrderStatusByOrderId(orderIds,status);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("订单接口  根据订单i查询异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据订单学生名称查询
     * @param studentName
     * @return
     */
    @GetMapping("/findOrderByStudentName/{studentName}")
    @ApiOperation(value = "根据订单学生名称查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentName", value = "学生名称", required = true,
                    paramType = "path", dataTypeClass = String.class),
    })
    public DataResult<List<TabOrderVO>> findOrderByStudentName(@PathVariable("studentName")String studentName){
        try {
            List<TabOrderDTO> dtoList = orderService.findOrderByStudentName(studentName);
            List<TabOrderVO> voListL = new ArrayList<>();
            dtoList.forEach(dto ->{
                TabOrderVO vo = new TabOrderVO();
                BeanUtils.copyProperties(dto,vo);
                voListL.add(vo);
            });
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(voListL);
        }catch (Exception e){
            logger.error("订单接口  根据订单i查询异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 增加订单
     * @param tabOrderDTO
     * @return
     */
    @PostMapping("/insertOrder")
    @ApiOperation(value = "增加订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tabOrderDTO", value = "订单实体", required = true,
                      dataTypeClass = TabOrderDTO.class),
    })
    public DataResult<Map<String,Object>> insertOrder(@RequestBody TabOrderDTO tabOrderDTO){
        try {
            Map<String,Object> map = orderService.insertOrder(tabOrderDTO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(map);
        }catch (Exception e){
            logger.error("订单接口  根据订单i查询异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据订单讲师名称查询
     * @param teacherName
     * @return
     */
    @PostMapping("/findOrderByTeacherName/{teacherName}")
    @ApiOperation(value = "根据订单讲师名称查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherName", value = "讲师名称", required = true,
                    paramType = "path", dataTypeClass = String.class),
    })
    public DataResult findOrderByTeacherName(@PathVariable("teacherName")String teacherName){
        try { 
            //Map<String,Object> dtoList = orderService.findOrderByTeacherName(teacherName);
            Map<String,Object> map = orderService.findOrderByTeacherName(teacherName);
            List<TabOrderVO> voListL = new ArrayList<>();
//            dtoList.forEach(dto ->{
//                TabOrderVO vo = new TabOrderVO();
//                BeanUtils.copyProperties(dto,vo);
//                voListL.add(vo);
//            });
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(map);
        }catch (Exception e){
            logger.error("订单接口  根据订单i查询异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
}
