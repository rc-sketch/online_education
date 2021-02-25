package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-30 15:06
 **/
@Controller
@RequestMapping("teacherController")
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherFeign teacherFeign;

    /**
     * 根据教室id查询
     * @param teacherId
     * @return
     */
    @RequestMapping("/findTeacherByTeacherId/{teacherId}")
    @ResponseBody
    public DataResult findTeacherByTeacherId(@PathVariable("teacherId") String teacherId){

        DataResult teacherByTeacherId = null;
        try {
            teacherByTeacherId = teacherFeign.findTeacherByTeacherId("101222");
            //logger.info("根据教师id查询出的数据为:", teacherByTeacherId);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacherByTeacherId);
        } catch (Exception e) {
            logger.info("根据教师id查询数据失败:", e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 增加教师信息
     * @param teacherVO
     * @return
     */
    @RequestMapping("insertTeacher")
    public DataResult insertTeacher(TabTeacherVO teacherVO){
        DataResult teacherByTeacherId = null;
        try {
            teacherByTeacherId = teacherFeign.insertTeacher(teacherVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacherByTeacherId);
        } catch (Exception e) {
            logger.info("根据教师id查询数据失败:", e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }



}

    