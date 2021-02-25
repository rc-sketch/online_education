package com.oe.service;

import com.oe.domain.dto.TabItemCatDTO;

import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service
 * @ClassName: CourseService
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 19:46
 * @Version: 1.0
 */
public interface ItemCatService {
    /***
     * 根据父级id查询
     * @param parentId
     * @return
     */
    List<TabItemCatDTO> findItemCatByParentId(Long parentId);

    /***
     * 根据id查询
     * @param id
     * @return
     */
    TabItemCatDTO findItemCatById(Long id);
}
