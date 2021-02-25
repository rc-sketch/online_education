package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabTeacherGiftDTO;
import com.oe.domain.vo.TabGiftVO;
import com.oe.domain.vo.TabTeacherGiftVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.service.TeacherGiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.controller
 * @ClassName: TeacherGiftController
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 19:26
 * @Version: 1.0
 */
@RestController
@Api(value = "教师收到礼物")
@RequestMapping("teacher_gift")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
public class TeacherGiftController {

    @Resource
    private TeacherGiftService teacherGiftService;

    @Resource
    private TeacherFeign teacherFeign;

    private Logger logger = LoggerFactory.getLogger(TeacherGiftController.class);

    /***
     * 查询课程信息并分页
     * @return
     */
    @GetMapping("/findTeacherGiftList")
    @ApiOperation(value = "查询教师收到的礼物")
    public DataResult<List<TabTeacherGiftVO>> findCoursePage(){
        try {
            List<TabTeacherGiftDTO> dtoList = teacherGiftService.findTeacherGiftList();
            List<TabTeacherGiftVO> voList = new ArrayList<>();
            dtoList.forEach((dto -> {
                TabTeacherGiftVO tabTeacherGiftVO = new TabTeacherGiftVO();
                BeanUtils.copyProperties(dto,tabTeacherGiftVO);
                // 根据礼物id查询礼物名称
                DataResult<TabGiftVO> data = teacherFeign.findGiftById(dto.getGiftId());
                TabGiftVO giftVO = data.getData();
                tabTeacherGiftVO.setGiftName(giftVO.getGiftName());
                voList.add(tabTeacherGiftVO);
            }));
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(voList);
        }catch (Exception e){
            logger.error("课程接口 查询课程信息 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

//    @GetMapping("test")
//    @ApiOperation(value = "测试礼物")
//    public DataResult test(){
//         DataResult<List<TabGiftVO>> giftList = teacherFeign.findGiftList();
//         return giftList;
//     }

}
