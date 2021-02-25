package com.oe.service.course;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oe.domain.dto.TabCourseDTO;
import com.oe.domain.dto.TabGiftDTO;
import com.oe.mapper.course.CourseMapper;
import com.oe.pojo.TabCourse;
import com.oe.pojo.TabGift;
import com.oe.service.CourseService;
import com.oe.util.PageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service.course
 * @ClassName: CourseServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 19:47
 * @Version: 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    /***
     * 根据主键查询对应课程信息
     * @param id
     * @return
     */
    @Override
    public TabCourseDTO findCourseById(Long id) {
        TabCourse tabCourse = courseMapper.selectById(id);
        TabCourseDTO tabCourseDTO = new TabCourseDTO();
        BeanUtils.copyProperties(tabCourse, tabCourseDTO);
        return tabCourseDTO;
    }

    /***
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteCourseById(Long[] ids) {
        courseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /***
     * 审核课程
     * @param ids
     * @param status
     */
    @Override
    public void updateCourseById(Long[] ids, String status) {
        for (int i = 0; i < ids.length; i++) {
            TabCourse course = new TabCourse();
            course.setId(ids[i]);
            course.setStatus(status);
            courseMapper.updateById(course);
        }
    }

    /***
     * 查询课程  分页
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public PageBean findCoursePage(Integer pageNumber, Integer pageSize) {
        // 第一个参数为当前页(pageNum),第二个参数为每页记录数(pageSize)
        Page<TabCourse> userPage = new Page<>(pageNumber, pageSize);
        IPage<TabCourse> userIPage = courseMapper.selectPage(userPage, null);
        // 将Pojo转成VO
        List<TabCourse> list = userIPage.getRecords();
        List<TabCourseDTO> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TabCourseDTO tabTeacherVO = new TabCourseDTO();
            BeanUtils.copyProperties(list.get(i), tabTeacherVO);
            list1.add(tabTeacherVO);
        }
        // 分页
        PageBean pageBean = new PageBean();
        pageBean.setResult(list1);
        pageBean.setPageNumber((int) userIPage.getCurrent());// 页数
        pageBean.setPageSize((int) userIPage.getSize());// 每页条数
        pageBean.setTotalCount((int) userIPage.getTotal());// 总条数
        pageBean.setTotalPage((int) userIPage.getPages());
        return pageBean;
    }

    /***
     * 增加课程信息
     * @param courseDTO
     */
    @Override
    public void insertCourse(TabCourseDTO courseDTO) {
        TabCourse course = new TabCourse();
        BeanUtils.copyProperties(courseDTO, course);
        courseMapper.insert(course);
    }

    @Override
    public List<TabCourseDTO> findisFree() {
        List<TabCourse> tabCourses = courseMapper.selectList(null);
        List<TabCourseDTO> dtoList = new ArrayList<>();
        tabCourses.forEach((pojo) -> {
            TabCourseDTO courseDTO = new TabCourseDTO();
            BeanUtils.copyProperties(pojo, courseDTO);
            dtoList.add(courseDTO);
        });
        return dtoList;
    }
}
