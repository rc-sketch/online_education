package com.oe.service;

import com.oe.domain.dto.TabCourseTagDTO;

import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service
 * @ClassName: CourseDescService
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 1:15
 * @Version: 1.0
 */
public interface CourseTagService {

    List<TabCourseTagDTO> findTagList();

    void deleteTagsByIds(Long[] ids);

    void insertTags(TabCourseTagDTO dto);

    TabCourseTagDTO getInfoById(Integer id);

    void updateTags(TabCourseTagDTO dto);
}
