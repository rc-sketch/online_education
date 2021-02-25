package com.oe.pojo;

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
public class TabUserScoreAccount implements Serializable {
    private Long accountId;

    private Long studentName;

    private Integer expendScore;

    private Integer totalScore;

    private Date createTime;

    private Date updateTime;
}