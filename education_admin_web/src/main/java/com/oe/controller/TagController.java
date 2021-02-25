package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabCourseTagVO;
import com.oe.feign.yzh.TeacherFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @ClassName TagController
 * @Author RC
 * @date 2021.01.31 20:53
 */
@RestController
@RequestMapping("tagController")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "课程标签")
public class TagController {
    @Autowired
    private TeacherFeign teacherFeign;
    private static final Logger logger = LoggerFactory.getLogger(TagController.class);


    @PostMapping("getTags")
    @ApiOperation(value = "查询标签信息")
    public DataResult getTags(){
        try {
            logger.info("查询标签信息");
            DataResult<List<TabCourseTagVO>> tagList = teacherFeign.findTagList();
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(tagList);
        }catch (Exception e){
            logger.info("查询失败 :{}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * 批量删除标签
     * @param ids
     * @return
     */
    @DeleteMapping("deleteBatch/{ids}")
    public DataResult deleteBatch(@PathVariable("ids")Long[] ids){
        try {
            DataResult<TabCourseTagVO> tabCourseTagVODataResult = teacherFeign.deleteTagsByIds(ids);
            return tabCourseTagVODataResult;
        }catch (Exception e){
            logger.error("删除标签异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * 增加标签
     * @param courseTagVO
     * @return
     */
    @PostMapping("addTags")
    @ResponseBody
    public DataResult addTags(TabCourseTagVO courseTagVO){
        try {
            teacherFeign.insertTags(courseTagVO);
           return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("增加标签异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @PostMapping("getInfoById/{id}")
    public DataResult getInfoById(@PathVariable("id")Integer id){
        try {
            DataResult<TabCourseTagVO> courseTagVODataResult = teacherFeign.getInfoById(id);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(courseTagVODataResult);
        }catch (Exception e){
            logger.error("查询标签异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * 修改标签
     * @param courseTagVO
     * @return
     */
    @PostMapping("updateTags")
    public DataResult updateTags(TabCourseTagVO courseTagVO){
        try {
            DataResult dataResult = teacherFeign.updateTags(courseTagVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("修改标签异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

}
