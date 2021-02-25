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
public class TabStudentAchievementVO implements Serializable {
    private Long id;

    private String studentName;

    private Long courseId;

    private String studentSay;

    private String studentFeel;

    private String studentNote;

 }