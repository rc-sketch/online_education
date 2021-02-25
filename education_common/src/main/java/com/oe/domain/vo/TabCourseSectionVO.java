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
public class TabCourseSectionVO implements Serializable {
    private Long id;

    private String text;

    private Long parentId;

    private Long descId;

    private String url;

}