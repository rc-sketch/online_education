package com.oe.service;

import com.oe.domain.dto.TabCourseDTO;
import com.oe.util.PageBean;

import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service
 * @ClassName: CourseService
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 19:46
 * @Version: 1.0
 */
public interface CourseService {
    void insertCourse(TabCourseDTO courseDTO);

    PageBean findCoursePage(Integer pageNumber, Integer pageSize);

    void updateCourseById(Long[] ids ,String status);

    void deleteCourseById(Long[] ids);

    TabCourseDTO findCourseById(Long id);

    List<TabCourseDTO> findisFree();

}
