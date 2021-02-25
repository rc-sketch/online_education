package com.oe.service;

import com.oe.domain.dto.TabTeacherGiftDTO;

import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service
 * @ClassName: TeacherGiftService
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 19:28
 * @Version: 1.0
 */
public interface TeacherGiftService {
    List<TabTeacherGiftDTO> findTeacherGiftList();
}
