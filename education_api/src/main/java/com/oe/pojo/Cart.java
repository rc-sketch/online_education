package com.oe.pojo;

import com.oe.domain.vo.TabCourseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @ClassName Cart
 * @Author RC
 * @date 2020.12.21 14:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Cart implements Serializable {

    private String teacherId;
    private String name;
    private List<TabCourseVO> courseVOList;

}
