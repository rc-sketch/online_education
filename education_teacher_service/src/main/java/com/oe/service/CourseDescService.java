package com.oe.service;

import com.oe.domain.dto.TabCourseDescDTO;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service
 * @ClassName: CourseDescService
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 1:15
 * @Version: 1.0
 */
public interface CourseDescService {
    void insertCourseDesc(TabCourseDescDTO courseVO);

    TabCourseDescDTO findCourseDescByCourseId(Long courseId);
}
