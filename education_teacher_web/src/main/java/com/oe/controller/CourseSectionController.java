package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabCourseSectionVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-02-01 00:59
 **/
@RestController
@RequestMapping("courseSection")
public class CourseSectionController {
    @Autowired
    private TeacherFeign teacherFeign;

    /**
     * 增加章节
     * @return
     */
    @RequestMapping("insertCourseSection")
    public DataResult insertCourseSection(TabCourseSectionVO courseSectionVO){

        try {
            ///ddfdfertgdryr
            DataResult dataResult = teacherFeign.insertCourseSection(courseSectionVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(dataResult.getData());
        } catch (Exception e) {
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询课程章节
     * @return
     */
    @RequestMapping("/findCourseSectionList")
    public List<Map<String,Object>> findCourseSectionList(){
        // Subject subject = SecurityUtils.getSubject();
        //TPerson person = (TPerson) subject.getPrincipal();
        List<Map<String, Object>> courseSectionList = teacherFeign.findCourseSectionList();
        return courseSectionList;
    }
}

    