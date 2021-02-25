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
/***
 * 教师收到的礼物
 */
public class TabTeacherGift implements Serializable {
    // 主键
    private Long id;
    // 教师名称
    private String teacherName;
    // 礼物ID
    private Long giftId;
    // 学生名称
    private String studentName;

}