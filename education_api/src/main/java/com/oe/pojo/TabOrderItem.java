package com.oe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TabOrderItem implements Serializable {
    private Long id;

    private Long courseId;

    private Long orderId;

    private String title;

    private BigDecimal price;

    private Integer num;

    private BigDecimal totalFee;

    private String picPath;

    private String teacherName;

  }