package com.oe.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;

/**
 * @ProjectName: dongdongshop_parent
 * @Package: com.yzh.config
 * @ClassName: AlipayBean
 * @Author: Y眼中人Y
 * @Date: 2020/12/24 - 1:10
 * @Version: 1.0
 */

public class AlipayBean {

    @Bean
    public AlipayClient alipayBean(){
        return new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    }
}
