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
public class TabOperateRecordVO implements Serializable {
    private Long id;

    private Date loginTime;

    private String city;

    private String ip;

    private String loginEquipment;

    private String operateSystem;

    private String userId;
}