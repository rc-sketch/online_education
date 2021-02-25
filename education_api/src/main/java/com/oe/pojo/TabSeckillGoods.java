package com.oe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
/***
 * 秒杀课程
 */
public class TabSeckillGoods implements Serializable {
    private Long id;

    private Long courseId;

    private String title;

    private String smallPic;

    private BigDecimal price;

    private BigDecimal costPrice;

    private String teacherName;

    private Date createTime;

    private Date checkTime;

    private String status;

    private Date startTime;

    private Date endTime;

    private Integer num;

    private Integer stockCount;

    private String introduction;

}