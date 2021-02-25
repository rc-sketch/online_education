package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabCourseSectionDTO;
import com.oe.domain.dto.TabTeacherDTO;
import com.oe.domain.vo.TabCourseSectionVO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.pojo.TabCourseSection;
import com.oe.service.CourseSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-31 21:17
 **/
@RestController
@RequestMapping("courseSectionController")
@RefreshScope ///nacos配置文件自动刷新
@Api(value = "课程章节")
public class CourseSectionController {
    private Logger logger = LoggerFactory.getLogger(CourseSectionController.class);
    @Autowired
    private TeacherFeign teacherFeign;

    @Autowired
    private CourseSectionService courseSectionService;


    @PostMapping("/insertCourseSection")
    @ApiOperation(value = "增加章节信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "TabCourseSectionVO", value = "课程章节对象",paramType = "body",
                    dataTypeClass = TabCourseVO.class),
    })
    public DataResult insertCourseSection(@RequestBody TabCourseSectionVO courseSectionVO){

        try {
            // 创建实体对象DTO
            TabCourseSectionDTO tabCourseSectionDTO = new TabCourseSectionDTO();
            BeanUtils.copyProperties(courseSectionVO,tabCourseSectionDTO);
            //添加章节
            courseSectionService.insertCourseSection(tabCourseSectionDTO);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("增加课程章节失败", e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findCourseSectionList")
    public List<Map<String,Object>> findCourseSectionList(){
       // Subject subject = SecurityUtils.getSubject();
        //TPerson person = (TPerson) subject.getPrincipal();
        List<Map<String,Object>> lists = courseSectionService.findCourseSectionList();
        return lists;
    }
}

    