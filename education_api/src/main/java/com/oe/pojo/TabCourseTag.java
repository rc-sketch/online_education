package com.oe.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 课程标签
 */
@TableName("tab_course_tag")
public class TabCourseTag implements Serializable {
    // @JsonSerialize(using= ToStringSerializer.class)
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String tag;

}