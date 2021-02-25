package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabCourseDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabItemCatVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.service.CourseService;
import com.oe.util.PageBean;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @ProjectName: online_education
 * @Package: com.oe.controller
 * @ClassName: CourseController
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 19:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("course")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "课程接口")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherFeign teacherFeign;

    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    /***
     * 增加课程
     * @tabTeacherVO
     * @return
     */
    @PostMapping("/insertCourse")
    @ApiOperation(value = "增加课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseVO", value = "课程对象",
                   paramType = "body" ,dataTypeClass = TabCourseVO.class),
    })
    public DataResult insertCourse(@RequestBody TabCourseVO courseVO){
        try {
            // 创建实体对象DTO
            TabCourseDTO courseDTO = new TabCourseDTO();
            BeanUtils.copyProperties(courseVO,courseDTO);
            // 修改教师信息
            // 审核状态
            courseDTO.setAuditStatus("0");// 待审核
            DataResult<TabItemCatVO> dataResult = teacherFeign.findItemCatById(courseDTO.getCategory2Id());
            TabItemCatVO data =  dataResult.getData();
            courseDTO.setCategory(data.getName());// 最后一级分类名称
            courseDTO.setCreateTime(new Date());// 创建时间
            Random random = new Random();
            int id = random.nextInt();
            long courseId = Long.parseLong(Math.abs(id) + "");
            courseDTO.setId(courseId);
            // 增加课程详情
            teacherFeign.insertDescCourse(courseId);
            courseService.insertCourse(courseDTO);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("课程接口 增加课程信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 查询课程信息并分页
     * @return
     */
    @GetMapping("/findCoursePage")
    @ApiOperation(value = "查询课程信息并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数", required = true,
                    dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true,
                    dataTypeClass = Integer.class),
    })
    public DataResult<PageBean> findCoursePage(@RequestParam(defaultValue = "1")Integer pageNumber, @RequestParam(defaultValue = "3")Integer pageSize){
        try {
            PageBean page = courseService.findCoursePage(pageNumber,pageSize);
            List<TabCourseDTO> result = page.getResult();
            List<TabCourseVO> list = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                TabCourseVO tabTeacherVO = new TabCourseVO();
                BeanUtils.copyProperties(result.get(i),tabTeacherVO);
                list.add(tabTeacherVO);
            }
            page.setResult(list);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(page);
        }catch (Exception e){
            logger.error("课程接口 查询课程信息 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 批量删除课程
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value = "课程ID", required = true,
                    paramType = "path", dataTypeClass = Long[].class)
    })
    public DataResult deleteCourseById(@PathVariable("ids")Long[] ids){
        try {
            courseService.deleteCourseById(ids);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("课程接口 删除课程异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }



    /***
     * 审核课程
     * @param ids
     * @return
     */
    @PutMapping("/{ids}")
    @ApiOperation(value = "审核课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "课程ID", required = true,
                    paramType = "path", dataTypeClass = Long[].class),
            @ApiImplicitParam(name = "status", value = "审核状态", required = true,
                    dataTypeClass = String.class),
    })
    public DataResult updateTeacherByTeacherId(@PathVariable("ids") Long[] ids,@RequestParam("status") String status){
        try {
//            // 创建实体对象DTO
//            TabCourseDTO courseDTO = new TabCourseDTO();
//            courseDTO.setId(id);
//            courseDTO.setStatus(status);
            // 修改教师信息
            courseService.updateCourseById(ids,status);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("课程接口  审核课程 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
    /***
     * 根据主键查询对应课程信息
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    @ApiOperation(value = "根据主键查询课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "课程id", required = true,
                    paramType = "path", dataTypeClass = Long.class),
    })
    public DataResult<TabCourseVO> findCourseById(@PathVariable("id") Long id){
        try {
            // 根据id查询
            TabCourseDTO tabCourseDTO = courseService.findCourseById(id);
            // 创建VO实体
            TabCourseVO courseVO = new TabCourseVO();
            // 将DTO转成VO
            BeanUtils.copyProperties(tabCourseDTO,courseVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(courseVO);
        }catch (Exception e){
            logger.error("课程接口 根据主键获取课程信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /**
     * 查询是否免费课程
     * @return
     */
    @GetMapping("findisFree")
    @ApiOperation(value = "查询是否免费课程")
    public DataResult<List<TabCourseVO>> findisFree(){
        try {
            List<TabCourseDTO> dtoList = courseService.findisFree();
            List<TabCourseVO> voList = new ArrayList<>();
            // 将DTO转成VO
            dtoList.forEach((dto) -> {
                TabCourseVO courseVO = new TabCourseVO();
                BeanUtils.copyProperties(dto,courseVO);
                voList.add(courseVO);
            });
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(voList);
        }catch (Exception e){
            logger.error("课程接口 查询课程信息 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }



}
