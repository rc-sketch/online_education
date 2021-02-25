package com.oe.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 17:47
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AlipayVO {
    private String outTradeNo;
    private String totalAmount;
    private String subject;
    private String body;
}
