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
 * 教师收到的礼物
 */
public class TabTeacherGiftVO implements Serializable {
    // 主键
    private Long id;
    // 教师名称
    private String teacherName;
    // 礼物ID
    private Long giftId;
    // 学生名称
    private String studentName;

    // 添加一个字段  giftName : 收到礼物的名称
    private String giftName;

}