package com.oe.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TabCourseSection implements Serializable {
    private Long id;

    private String text;

    private Long parentId;

    private Long descId;

    private String url;

}