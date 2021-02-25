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
public class TabCashAccountVO implements Serializable {
    private Long id;

    private String teacherName;

    private Date createTime;

    private Date updateTime;

    private String totalCash;

}