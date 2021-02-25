package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabOrderVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.OrderFeign;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @ClassName CourseController
 * @Author RC
 * @date 2021.01.31 15:41
 */
@RestController
@RequestMapping("courseController")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新1
@Api(value = "课程服务")
public class CourseController {

    @Autowired
    private TeacherFeign teacherFeign;
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("getCourseList")
    @ApiOperation(value = "分页展示课程信息")
    @ResponseBody
    public DataResult findTeacherPage(@RequestParam(defaultValue = "1")Integer pageNumber, @RequestParam(defaultValue = "3")Integer pageSize){
        try {
            //分页展示教师信息根据教师名称及等级模糊查询
            DataResult<PageBean> teacherPage = teacherFeign.findCoursePage(pageNumber, pageSize);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(teacherPage);
        }catch (Exception e){
            logger.info("失败 :{}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteBath/{ids}")
    @ApiOperation(value = "批量删除课程信息")
    @ResponseBody
    public DataResult deleteBath(@PathVariable("ids")Long[] ids){
        try {
            //分页展示教师信息根据教师名称及等级模糊查询,
            DataResult dataResult = teacherFeign.deleteCourseById(ids);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.info("失败 :{}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("updateStatus/{ids}")
    @ApiOperation(value = "审核状态")
    @ResponseBody
    public DataResult updateStatus(@PathVariable("ids") Long[] ids,String status){
        try {
            //提交课程审核状态
            DataResult dataResult = teacherFeign.updateTeacherByTeacherId(ids, status);
            if(status.equals("1")){
                //审核通过发送mq消息
                rocketMQTemplate.convertAndSend("approvedCourseSendSuccess",ids);
            }
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.info("失败 :{}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

}
