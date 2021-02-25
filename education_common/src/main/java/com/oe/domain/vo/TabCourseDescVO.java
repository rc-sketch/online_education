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
 * 课程详情
 */
public class TabCourseDescVO implements Serializable {
    private Long id;

    private String usefulLife;

    private Integer classContentTime;

    private Integer teachServiceTime;

    private Integer videoTime;

    private Integer peopleCounting;

    private String learnResult;

    private String courseDescIntroduction;

    private Integer goodReputation;

    private Long courseId;
}