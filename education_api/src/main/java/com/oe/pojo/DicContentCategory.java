package com.oe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/***
 * 广告分类表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DicContentCategory implements Serializable {
    private Long id;// 主键
    private String name;// 分类名称
}