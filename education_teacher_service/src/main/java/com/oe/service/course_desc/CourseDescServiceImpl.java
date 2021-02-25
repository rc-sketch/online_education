package com.oe.service.course_desc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oe.domain.dto.TabCourseDTO;
import com.oe.domain.dto.TabCourseDescDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.mapper.course_desc.CourseDescMapper;
import com.oe.pojo.TabCourse;
import com.oe.pojo.TabCourseDesc;
import com.oe.service.CourseDescService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service.course_desc
 * @ClassName: CourseDescServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 1:15
 * @Version: 1.0
 */
@Service
public class CourseDescServiceImpl implements CourseDescService {

    @Resource
    private CourseDescMapper courseDescMapper;

    /***
     * 增加课程详情
     * @param courseVO
     */
    @Override
    public void insertCourseDesc(TabCourseDescDTO courseVO) {
        TabCourseDesc courseDesc = new TabCourseDesc();
        BeanUtils.copyProperties(courseVO,courseDesc);
        courseDescMapper.insert(courseDesc);
    }
    /***
     * 根据课程id查询对应课程详情表信息
     * @param courseId
     * @return
     */
    @Override
    public TabCourseDescDTO findCourseDescByCourseId(Long courseId) {
        QueryWrapper<TabCourseDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<TabCourseDesc> tabCourseDescs = courseDescMapper.selectList(queryWrapper);
        TabCourseDescDTO tabCourseDescDTO = null;
        for (TabCourseDesc tabCourseDesc : tabCourseDescs) {
            tabCourseDescDTO = new TabCourseDescDTO();
            BeanUtils.copyProperties(tabCourseDesc,tabCourseDescDTO);
        }

        return tabCourseDescDTO;
    }
}
