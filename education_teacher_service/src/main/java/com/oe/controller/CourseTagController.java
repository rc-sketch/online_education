package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabCourseTagDTO;
import com.oe.domain.vo.TabCourseTagVO;
import com.oe.service.CourseTagService;
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
import java.util.List;

/**
 * @Description
 * @ClassName CourseTagController
 * @Author RC
 * @date 2021.02.07 12:55
 */
@RestController
@RequestMapping("tags")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "标签接口")
public class CourseTagController {
    @Resource
    private CourseTagService courseTagService;

    private Logger logger = LoggerFactory.getLogger(CourseTagController.class);

    /***
     * 标签查询
     * @return
     */
    @PostMapping("/findTagList")
    @ApiOperation(value = "标签查询")
    public DataResult<List<TabCourseTagVO>> findTagList(){

        try {
            // 获取所有礼物信息
            List<TabCourseTagDTO> dtoList = courseTagService.findTagList();
            List<TabCourseTagVO> voList = new ArrayList<>();
            // 将DTO转成VO
            dtoList.forEach((dto) -> {
                TabCourseTagVO tagVO = new TabCourseTagVO();
                BeanUtils.copyProperties(dto,tagVO);
                voList.add(tagVO);
            });
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(voList);
        }catch (Exception e){
            logger.error("礼物接口 查询礼物信息 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 批量删除标签
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value = "标签ID", required = true,
                    paramType = "path", dataTypeClass = Long[].class)
    })
    public DataResult deleteTagsByIds(@PathVariable("ids")Long[] ids){
        try {
            courseTagService.deleteTagsByIds(ids);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("标签接口 删除标签异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 增加标签
     * @param tabCourseTagVO
     * @return
     */
    @PostMapping("/insertTags")
    @ApiOperation(value = "标签增加")
    @ApiImplicitParam(name="tabCourseTagVO",value = "标签实体", required = true,
            paramType = "body", dataTypeClass = TabCourseTagVO.class)
    public DataResult insertTags(@RequestBody TabCourseTagVO tabCourseTagVO){
        try {
            TabCourseTagDTO dto = new TabCourseTagDTO();
            BeanUtils.copyProperties(tabCourseTagVO,dto);
            courseTagService.insertTags(dto);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("标签接口 增加标签 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 批量id查询标签
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    @ApiOperation(value = "查询标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "标签ID", required = true,
                    paramType = "path", dataTypeClass = Integer.class)
    })
    public DataResult getInfoById(@PathVariable("id")Integer id){
        try {
            TabCourseTagDTO dTO = courseTagService.getInfoById(id);
            TabCourseTagVO vO = new TabCourseTagVO();
            BeanUtils.copyProperties(dTO,vO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(vO);
        }catch (Exception e){
            logger.error("标签接口 查询标签异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
    /***
     * 修改标签
     * @param tabCourseTagVO
     * @return
     */
    @PostMapping("/updateTags")
    @ApiOperation(value = "标签修改")
    @ApiImplicitParam(name="tabCourseTagVO",value = "标签实体", required = true,
            paramType = "body", dataTypeClass = TabCourseTagVO.class)
    public DataResult updateTags(@RequestBody TabCourseTagVO tabCourseTagVO){
        try {
            TabCourseTagDTO dto = new TabCourseTagDTO();
            BeanUtils.copyProperties(tabCourseTagVO,dto);
            courseTagService.updateTags(dto);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("标签接口 增加标签 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
}
