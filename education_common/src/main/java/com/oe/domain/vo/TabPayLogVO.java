package com.oe.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TabPayLogVO implements Serializable {
    private String outTradeNo;

    private Date createTime;

    private Date payTime;

    private Long totalFee;

    private String studentId;

    private String transactionId;

    private String tradeState;

    private String orderList;

    private String payType;

}