package com.oe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TabStudentAchievement implements Serializable {
    private Long id;

    private String studentName;

    private Long courseId;

    private String studentSay;

    private String studentFeel;

    private String studentNote;

 }