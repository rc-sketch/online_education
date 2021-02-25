package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
@Api (value = "教师信息")
public class TeacherController {

    @Autowired
    private TeacherFeign teacherFeign;
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    /**
     * 根据教师id查询
     * @param teacherId
     * @return
     */
    @GetMapping("getDetail/{teacherId}")
    @ResponseBody
    public DataResult getDetail(@PathVariable("teacherId") String teacherId){

        try {
            //根据教师id查询详情页展示
            logger.info("根据教师id查询信息");
            DataResult teacher = teacherFeign.findTeacherByTeacherId(teacherId);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacher);
        } catch (Exception e) {
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getTeacherList")
    @ApiOperation(value = "分页展示教师信息")
    @ResponseBody
    public DataResult findTeacherPage(@RequestParam(defaultValue = "1")Integer pageNumber, @RequestParam(defaultValue = "3")Integer pageSize,@RequestParam String name,@RequestParam String levelName){
        try {
            //分页展示教师信息根据教师名称及等级模糊查询
            DataResult<PageBean> teacherPage = teacherFeign.findTeacherPage(pageNumber, pageSize, name,levelName);
               return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacherPage);
        }catch (Exception e){
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("getStatus")
    @ApiOperation(value = "审核状态")
    @ResponseBody
    public DataResult getStatus(String teacherId,String status){
        try {
            //修改教师审核状态
               DataResult dataResult = teacherFeign.updateTeacherByTeacherId(teacherId,status);
               return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("deleteInfo/{teacherId}")
    @ApiOperation(value = "删除教师信息")
    @ResponseBody
    public DataResult deleteInfo(@PathVariable("teacherId") String teacherId) {
        try {
            //根据教师id删除信息
            DataResult<TabTeacherVO> result = teacherFeign.deleteTeacherByTeacherId(teacherId);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        } catch (Exception e) {
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

}

    