package com.oe.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: dongdongshop_parent
 * @Package: com.yzh.config
 * @ClassName: AlipayBean
 * @Author: Y眼中人Y
 * @Date: 2020/12/24 - 1:10
 * @Version: 1.0
 */
@Configuration
public class AlipayConfigBean {

    @Bean
    public AlipayClient alipayBean(){
        return new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    }

    @Bean
    public AlipayTradePagePayRequest alipayTradePagePayRequest(){
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        return alipayRequest;
    }
    @Bean
    public AlipayTradeQueryRequest alipayTradeQueryRequest(){
        return new AlipayTradeQueryRequest();
    }
    @Bean
    public AlipayTradeFastpayRefundQueryRequest alipayTradeFastpayRefundQueryRequest(){
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        return alipayRequest;
    }

    @Bean
    public AlipayTradeRefundRequest alipayTradeRefundRequest(){
        return new AlipayTradeRefundRequest();
    }
}
