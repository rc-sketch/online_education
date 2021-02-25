package com.oe.domain.dto;

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
 * 课程分类
 */
public class TabItemCatDTO implements Serializable {
    private Long id;

    private String name;

    private Long parentId;

}