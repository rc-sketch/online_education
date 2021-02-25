package com.oe.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
/***
 * 广告表
 */
public class TabContentVO implements Serializable {
    // 主键
    private Long id;
    // 分类id
    private Long categoryId;
    // 标题
    private String title;
    // 广告地址
    private String url;
    // 广告封面
    private String pic;
    // 状态
    private String status;
    // 排序值
    private Integer sortOrder;

}