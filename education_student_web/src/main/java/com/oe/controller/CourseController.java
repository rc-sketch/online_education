package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabCourseVO;
import com.oe.feign.yzh.TeacherFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: 邢孟君
 * @create: 2021-02-02 21:37
 **/
@RestController
@RequestMapping("courseController")
public class CourseController {

    private Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private TeacherFeign teacherFeign;

    @RequestMapping("findisFree")
    public DataResult<List<TabCourseVO>> findisFree(){

        try {
            DataResult<List<TabCourseVO>> listDataResult = teacherFeign.findisFree();
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(listDataResult.getData());
        } catch (Exception e) {
            logger.error("查询失败", e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
}

    