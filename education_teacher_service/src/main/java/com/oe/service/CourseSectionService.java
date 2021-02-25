package com.oe.service;

import com.oe.domain.dto.TabCourseSectionDTO;

import java.util.List;
import java.util.Map;

public interface CourseSectionService {
    void insertCourseSection(TabCourseSectionDTO tabCourseSectionDTO);

    List<Map<String, Object>> findCourseSectionList();
}
