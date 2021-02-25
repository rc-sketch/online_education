package com.oe.feign.yzh;

import com.oe.data.DataResult;
import com.oe.domain.dto.TabCourseDescDTO;
import com.oe.domain.vo.*;
import com.oe.util.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: online_education
 * @Package: com.oe.feign.yzh
 * @ClassName: TeacherFeign  教师
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 13:42
 * @Version: 1.0
 */
@FeignClient(value = "education-teacher-service")
public interface TeacherFeign {

    //////////// 讲师管理模块crud ////////////////////////////////////
    /***
     * 根据主键查询教师信息
     * @param teacherId
     * @return
     */
    @GetMapping("/teacher/{teacherId}")
    DataResult<TabTeacherVO> findTeacherByTeacherId(@PathVariable("teacherId") String teacherId);

    /***
     * 修改教师信息(审核)
     * @param teacherId
     * @param status
     * @return
     */
    @PutMapping("/teacher/{teacherId}")
    DataResult updateTeacherByTeacherId(@PathVariable("teacherId") String teacherId,@RequestParam("status")String status);

    /***
     * 增加教师信息
     * @param teacherVO
     * @return
     */
    @PostMapping("/teacher/insertTeacher}")
    DataResult insertTeacher(TabTeacherVO teacherVO);


    /***
     * 删除教师信息
     * @param teacherId
     * @return
     */
    @DeleteMapping("/teacher/{teacherId}")
    DataResult<TabTeacherVO> deleteTeacherByTeacherId(@PathVariable("teacherId") String teacherId);

    /***
     * 查询教师信息并分页
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/teacher/findTeacherPage")
    DataResult<PageBean> findTeacherPage(@RequestParam(defaultValue = "1")Integer pageNumber, @RequestParam(defaultValue = "3")Integer pageSize, @RequestParam String name, @RequestParam String levelName);

    /***
     * 根据教师名称查询
     * @param name
     * @return
     */
    @GetMapping("/teacher/findTeacherByName/{name}")
    DataResult<TabTeacherVO> findTeacherByName(@PathVariable("name") String name);


    //////////// 课程管理模块crud ////////////////////////////////////

    /**
     * 课程分类根据父级id查询
     * @param parentId
     * @return
     */
    @GetMapping("/itemCat/findItemCatByParentId/{parentId}")
    DataResult findItemCatByParentId(@PathVariable("parentId")Long parentId);

    /***
     * 根据主键查询课程分类
     * @param id
     * @return
     */
    @GetMapping("/itemCat/{id}")
    DataResult<TabItemCatVO> findItemCatById(@PathVariable("id") Long id);

    /***
     * 增加课程详情
     * @return
     */
    @PostMapping("/course_desc/insertCourseDesc")
    DataResult insertDescCourse(Long courseId);

    /***
     * 根据课程id查询课程详情
     * @return
     */
    @PostMapping("/course_desc/{courseId}")
    DataResult<TabCourseDescVO> findCourseDescByCourseId(@PathVariable("courseId")Long courseId);

    /***
     *增加课程
     * @param courseVO
     * @return
     */
    @PostMapping("/course/insertCourse")
    DataResult insertCourse(@RequestBody TabCourseVO courseVO);


    /***
     * 查询课程信息并分页
     * @return
     */
    @GetMapping("/course/findCoursePage")
    DataResult<PageBean> findCoursePage(@RequestParam(defaultValue = "1")Integer pageNumber, @RequestParam(defaultValue = "3")Integer pageSize);


    /***
     * 审核课程
     * @param ids
     * @return
     */
    @PutMapping("/course/{ids}")
    DataResult updateTeacherByTeacherId(@PathVariable("ids") Long[] ids,@RequestParam("status") String status);

    /***
     * 批量删除课程
     * @param ids
     * @return
     */
    @DeleteMapping("/course/{ids}")
    DataResult deleteCourseById(@PathVariable("ids")Long[] ids);

    /***
     * 查询是否免费课程
     * @return
     */
    @GetMapping("/course/findisFree")
    DataResult<List<TabCourseVO>> findisFree();


    /***
     * 根据id查询课程
     * @return
     */
    @PostMapping("/course/{id}")
    DataResult<TabCourseVO> findCourseById(@PathVariable("id")Long id);

    //////////// 礼物管理模块crud ////////////////////////////////////
    /***
     * 礼物查询
     * @return
     */
    @GetMapping("/gift/findGiftList")
    DataResult<List<TabGiftVO>> findGiftList();

    /***
     * 根据id查询礼物
     * @return
     */
    @GetMapping("/gift/{id}")
    DataResult<TabGiftVO> findGiftById(@PathVariable("id")Long id);

    /***
     * 根据id删除礼物
     * @return
     */
    @DeleteMapping("/gift/{ids}")
    DataResult<TabGiftVO> deleteGiftByIds(@PathVariable("ids")Long[] ids);

    /***
     * 增加礼物
     * @param tabGiftVO
     * @return
     */
    @PostMapping("/gift/insertGift")
    DataResult insertGift(@RequestBody TabGiftVO tabGiftVO);

    //////////// 课程管理模块crud ////////////////////////////////////
    /**
     *
     * 增加课程章节
     * @param tabCourseSectionVO
     * @return
     */
    @PostMapping("/courseSectionController/insertCourseSection")
    DataResult insertCourseSection(@RequestBody TabCourseSectionVO tabCourseSectionVO);

    /**
     *
     * 查询课程章节
     * @return
     */
    @GetMapping("/findCourseSectionList")
    List<Map<String,Object>> findCourseSectionList();

    /***
     * 查询教师收到礼物信息并分页
     * @return
     */
    @GetMapping("/teacher_gift/findTeacherGiftList")
    DataResult<List<TabTeacherGiftVO>> findCoursePage();


    //////////// 课程标签模块 crud ////////////
    /**
     * @Description
     * @Param
     * @return
     * @Author RC
     * @Date 2021.02.06 9:34
     **/

    @PostMapping("/tags/findTagList")
    DataResult<List<TabCourseTagVO>> findTagList();

    /***
     * 根据id删除标签
     * @return
     */
    @DeleteMapping("/tags/{ids}")
    DataResult<TabCourseTagVO> deleteTagsByIds(@PathVariable("ids")Long[] ids);

    /***
     * 增加标签
     * @param tabCourseTagVO
     * @return
     */
    @PostMapping("/tags/insertTags")
    DataResult insertTags(@RequestBody TabCourseTagVO tabCourseTagVO);

    /***
     * 根据标签id查询标签(回显)
     * @return
     */
    @PostMapping("/tags/{id}")
    DataResult<TabCourseTagVO> getInfoById(@PathVariable("id")Integer id);

    /***
     * 修改标签
     * @return
     */
    @PostMapping("/tags/updateTags")
    DataResult updateTags(@RequestBody TabCourseTagVO tabCourseTagVO);

    /***
     * 根据礼物id查询礼物(回显)
     * @return
     */
    @PostMapping("/gift/{id}")
    DataResult getInfoByGiftId(@PathVariable("id")Integer id);

    /***
     * 修改标签
     * @return
     */
    @PostMapping("/gift/updateGiftInfo")
    DataResult updateGiftInfo(@RequestBody TabGiftVO giftVO);
}
