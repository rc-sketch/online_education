package com.oe.service.course_tag;

import com.oe.domain.dto.TabCourseTagDTO;
import com.oe.mapper.course_tag.CourseTagMapper;
import com.oe.pojo.TabCourseTag;
import com.oe.service.CourseTagService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @ClassName CourseTagController
 * @Author RC
 * @date 2021.02.07 12:55
 */
@Service
public class CourseTagServiceImpl implements CourseTagService {

    @Resource
    private CourseTagMapper courseTagMapper;

    /***
     * 查询所有标签
     * @return
     */
    @Override
    public List<TabCourseTagDTO> findTagList() {
        List<TabCourseTag> tabGifts = courseTagMapper.selectList(null);
        List<TabCourseTagDTO> dtoList = new ArrayList<>();
        tabGifts.forEach((pojo) -> {
            TabCourseTagDTO dto = new TabCourseTagDTO();
            BeanUtils.copyProperties(pojo,dto);
            dtoList.add(dto);
        });
        return dtoList;
    }

    /***
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteTagsByIds(Long[] ids) {
        courseTagMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /***
     * 增加
     * @param dto
     */
    @Override
    public void insertTags(TabCourseTagDTO dto) {
        TabCourseTag pojo = new TabCourseTag();
        BeanUtils.copyProperties(dto,pojo);
        courseTagMapper.insert(pojo);
    }

    /***
     * 根据标签id查询
     * @param id
     */
    @Override
    public TabCourseTagDTO getInfoById(Integer id) {
        TabCourseTag pojo = courseTagMapper.selectById(id);
        TabCourseTagDTO dto = new TabCourseTagDTO();
        BeanUtils.copyProperties(pojo,dto);
        return dto;
    }

    /***
     * 修改标签
     * @param dto
     */
    @Override
    public void updateTags(TabCourseTagDTO dto) {
        TabCourseTag pojo = new TabCourseTag();
        BeanUtils.copyProperties(dto,pojo);
        courseTagMapper.updateById(pojo);
    }
}
