package com.oe.service;

import com.oe.domain.dto.TabPayLogDTO;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.service
 * @ClassName: PayLogService
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:55
 * @Version: 1.0
 */
public interface PayLogService {
    void insertPayLog(TabPayLogDTO courseDTO);

    TabPayLogDTO findPayLogByOutTradeNo(String outTradeNo);
}
