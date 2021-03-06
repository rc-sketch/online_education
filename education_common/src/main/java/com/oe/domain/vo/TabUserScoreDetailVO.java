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
public class TabUserScoreDetailVO implements Serializable {
    private Long detailId;

    private Long studentName;

    private Integer score;

    private Date createTime;

    private Date overdueTime;

    private String remarks;

}