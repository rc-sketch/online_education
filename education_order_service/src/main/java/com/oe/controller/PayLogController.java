package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabCourseDTO;
import com.oe.domain.dto.TabPayLogDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabItemCatVO;
import com.oe.domain.vo.TabPayLogVO;
import com.oe.service.PayLogService;
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
import java.util.Date;
import java.util.Random;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.controller
 * @ClassName: PayLogController
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:54
 * @Version: 1.0
 */
@RestController
@RequestMapping("pay_log")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "支付日志接口")
public class PayLogController {

    @Resource
    private PayLogService payLogService;

    private Logger logger = LoggerFactory.getLogger(PayLogController.class);


    /***
     * 增加日志
     * @tabTeacherVO
     * @return
     */
    @PostMapping("/insertPayLog")
    @ApiOperation(value = "增加支付日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payLogVO", value = "支付日志对象",
                    dataTypeClass = TabPayLogVO.class),
    })
    public DataResult insertPayLog(@RequestBody TabPayLogVO payLogVO){
        try {
            // 创建实体对象DTO
            TabPayLogDTO courseDTO = new TabPayLogDTO();
            BeanUtils.copyProperties(payLogVO,courseDTO);
            payLogService.insertPayLog(courseDTO);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("支付日志接口 增加日志异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }


    /***
     * 根据商家订单号(out_trade_no)查询支付日志
     * @outTradeNo
     * @return
     */
    @GetMapping("/{outTradeNo}")
    @ApiOperation(value = "根据商家订单号(out_trade_no)查询支付日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "outTradeNo", value = "支付日志对象",
                    dataTypeClass = String.class),
    })
    public DataResult fidPayLogByOutTradeNo(@PathVariable("outTradeNo") String outTradeNo){
        try {
            TabPayLogDTO dto = payLogService.findPayLogByOutTradeNo(outTradeNo);
            TabPayLogVO vo = new TabPayLogVO();
            BeanUtils.copyProperties(dto,vo);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(vo);
        }catch (Exception e){
            logger.error("支付日志接口 增加日志异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
}
