package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabCourseDTO;
import com.oe.domain.dto.TabItemCatDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabItemCatVO;
import com.oe.service.CourseService;
import com.oe.service.ItemCatService;
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
 * @ProjectName: online_education
 * @Package: com.oe.controller
 * @ClassName: CourseController
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 19:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("itemCat")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "课程分类服务")
public class ItemCatController {

    @Resource
    private ItemCatService itemCatService;

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    /***
     * 根据parentId查询
     * @param parentId
     * @return
     */
    @GetMapping("/findItemCatByParentId/{parentId}")
    @ApiOperation(value = "课程分类根据父级id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级ID", required = true,
                    paramType = "path", dataTypeClass = Long.class),
    })
    public DataResult<TabItemCatVO> findItemCatByParentId(@PathVariable("parentId") Long parentId) {
        try {
            List<TabItemCatDTO> list = itemCatService.findItemCatByParentId(parentId);
            List<TabItemCatVO> list2 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                TabItemCatVO tabItemCatVO = new TabItemCatVO();
                BeanUtils.copyProperties(list.get(i), tabItemCatVO);
                list2.add(tabItemCatVO);
            }
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(list2);
        } catch (Exception e) {
            logger.error("课程分类 根据父级id获取数据异常 :{}", e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "课程分类根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true,
                    paramType = "path", dataTypeClass = Long.class),
    })
    public DataResult findItemCatById(@PathVariable("id") Long id) {
        try {
            TabItemCatDTO list = itemCatService.findItemCatById(id);
            TabItemCatVO tabItemCatVO = new TabItemCatVO();
            BeanUtils.copyProperties(list, tabItemCatVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(tabItemCatVO);
        } catch (Exception e) {
            logger.error("课程分类 根据父级id获取数据异常 :{}", e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

}
