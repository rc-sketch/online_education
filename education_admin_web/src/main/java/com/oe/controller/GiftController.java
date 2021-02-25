package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabCourseTagVO;
import com.oe.domain.vo.TabGiftVO;
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
 * @ClassName GiftController
 * @Author RC
 * @date 2021.01.31 20:53
 */
@RestController
@RequestMapping("giftController")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "教师礼物")
public class GiftController {
    @Autowired
    private TeacherFeign teacherFeign;
    private static final Logger logger = LoggerFactory.getLogger(GiftController.class);


    @GetMapping("getGiftList")
    @ApiOperation(value = "展示礼物信息")
    public DataResult getGiftList(){
        try {
            //分页展示教师信息根据教师名称及等级模糊查询
            DataResult<List<TabGiftVO>> giftList = teacherFeign.findGiftList();
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(giftList);
        }catch (Exception e){
            logger.info("查询失败 :{}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * 批量删除礼物
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteBatch/{ids}")
    @ResponseBody
    public DataResult deleteBatch(@PathVariable("ids")Long[] ids){
        try {
            DataResult<TabGiftVO> tabGiftVODataResult = teacherFeign.deleteGiftByIds(ids);
            return tabGiftVODataResult;
        }catch (Exception e){
            logger.error("删除礼物异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * 增加礼物
     * @param tabGiftVO
     * @return
     */
    @PostMapping("addGift")
    @ResponseBody
    public DataResult addGift(TabGiftVO tabGiftVO){
        try {
            teacherFeign.insertGift(tabGiftVO);
           return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("增加礼物异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
    /***
     * 根据id查询礼物
     * @param id
     * @return
     */
    @PostMapping("getInfoByGiftId/{id}")
    public DataResult getInfoById(@PathVariable("id")Integer id){
        try {
            DataResult<TabGiftVO> giftVODataResult = teacherFeign.getInfoByGiftId(id);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(giftVODataResult);
        }catch (Exception e){
            logger.error("查询礼物异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
    /***
     * 修改礼物
     * @param giftVO
     * @return
     */
    @PostMapping("updateGiftInfo")
    public DataResult updateTags(TabGiftVO giftVO){
        try {
            DataResult dataResult = teacherFeign.updateGiftInfo(giftVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("修改礼物异常 {}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
