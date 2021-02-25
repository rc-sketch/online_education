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
 * 课程表
 */
public class TabCourse implements Serializable {
    // 主键
    private Long id;
    // 教师名称
    private String teacherName;
    // 课程名称
    private String courseName;
    // 课程简介
    private String courseIntroduction;
    // 课程状态   0审核中1审核通过2审核未通过3已删除4未申请
    private String auditStatus;
    // 是否上架   1.上架   2.未上架
    private String isMarketable;
    // 副标题
    private String caption;
    // 一级类目   tab_item_cat
    private Long category1Id;
    // 二级类目
    private Long category2Id;
    // 小图
    private String smallPic;
    // 售价
    private BigDecimal price;
    // 卖点
    private String sellPoint;
    // 封面
    private String image;
    // 状态
    private String status;
    // 修改时间
    private Date updateTime;
    // 创建时间

    private Date createTime;
    // 分类名称
    private String category;
    // 标题
    private String title;
    // 是否免费   1.免费  2.付费
    private String isFree;
    // 备用1
    private String field1;
    // 备用2
    private String field2;

}