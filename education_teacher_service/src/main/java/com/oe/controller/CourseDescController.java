package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabCourseDTO;
import com.oe.domain.dto.TabCourseDescDTO;
import com.oe.domain.vo.TabCourseDescVO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabItemCatVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.pojo.TabCourseDesc;
import com.oe.service.CourseDescService;
import com.oe.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ProjectName: online_education
 * @Package: com.oe.controller
 * @ClassName: CourseController
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 19:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("course_desc")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "课程详情服务")
public class CourseDescController {

    @Resource
    private CourseDescService courseDescService;

    private Logger logger = LoggerFactory.getLogger(CourseDescController.class);

    /***
     * 增加课程详情
     * @tabTeacherVO
     * @return
     */
    @PostMapping("/insertCourseDesc")
    @ApiOperation(value = "增加课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程ID",
                    dataTypeClass = Long.class),
    })
    public DataResult insertDescCourse(Long courseId){
        try {
            TabCourseDescDTO courseVO = new TabCourseDescDTO();
            courseVO.setCourseId(courseId);
            courseDescService.insertCourseDesc(courseVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("课程接口 增加课程详情信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }


    /***
     * 根据课程id查询对应课程详情信息
     * @param courseId
     * @return
     */
    @PostMapping("/{courseId}")
    @ApiOperation(value = "根据主键查询课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true,
                    paramType = "path", dataTypeClass = Long.class),
    })
    public DataResult<TabCourseDescVO> findCourseDescByCourseId(@PathVariable("courseId") Long courseId){
        try {
            // 根据id查询
            TabCourseDescDTO tabCourseDescDTO = courseDescService.findCourseDescByCourseId(courseId);
            // 创建VO实体
            TabCourseDescVO courseDescVO = new TabCourseDescVO();
            // 将DTO转成VO
            BeanUtils.copyProperties(tabCourseDescDTO,courseDescVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(courseDescVO);
        }catch (Exception e){
            logger.error("课程接口 根据主键获取课程信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

}
