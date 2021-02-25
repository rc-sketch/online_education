package com.oe.service.pay_log;

import com.oe.domain.dto.TabPayLogDTO;
import com.oe.mapper.pay_log.PayLogMapper;
import com.oe.pojo.TabPayLog;
import com.oe.service.PayLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.service.pay_log
 * @ClassName: PayLogServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:57
 * @Version: 1.0
 */
@Service
public class PayLogServiceImpl implements PayLogService {

    @Resource
    private PayLogMapper payLogMapper;

    @Override
    public TabPayLogDTO findPayLogByOutTradeNo(String outTradeNo) {
        Map<String,Object> map = new HashMap<>();
        map.put("out_trade_no",outTradeNo);
        List<TabPayLog> tabPayLogs = payLogMapper.selectByMap(map);
        TabPayLog payLog = tabPayLogs.get(0);
        TabPayLogDTO dto = new TabPayLogDTO();
        BeanUtils.copyProperties(payLog,dto);
        return dto;
    }


    @Override
    public void insertPayLog(TabPayLogDTO courseDTO) {
        TabPayLog payLog = new TabPayLog();
        BeanUtils.copyProperties(courseDTO,payLog);
        payLogMapper.insert(payLog);
    }
}
