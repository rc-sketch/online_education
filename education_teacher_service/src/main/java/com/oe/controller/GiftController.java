package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabCourseTagDTO;
import com.oe.domain.dto.TabGiftDTO;
import com.oe.domain.vo.TabCourseTagVO;
import com.oe.domain.vo.TabGiftVO;
import com.oe.pojo.TabGift;
import com.oe.service.GiftService;
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
 * @ClassName: GiftController
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 18:16
 * @Version: 1.0
 */
@RestController
@RequestMapping("gift")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "礼物接口")
public class GiftController {

    @Resource
    private GiftService giftService;

    private Logger logger = LoggerFactory.getLogger(GiftController.class);

    /***
     * 礼物查询
     * @return
     */
    @GetMapping("/findGiftList")
    @ApiOperation(value = "礼物查询")
    public DataResult<List<TabGiftVO>> findGiftList(){

        try {
            // 获取所有礼物信息
            List<TabGiftDTO> dtoList = giftService.findGiftList();
            List<TabGiftVO> voList = new ArrayList<>();
            // 将DTO转成VO
            dtoList.forEach((dto) -> {
                TabGiftVO giftVO = new TabGiftVO();
                BeanUtils.copyProperties(dto,giftVO);
                voList.add(giftVO);
            });
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(voList);
        }catch (Exception e){
            logger.error("礼物接口 查询礼物信息 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据id删除礼物
     * @return
     */
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据id删除礼物")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value = "礼物ID", required = true,
                    paramType = "path", dataTypeClass = Long[].class)
    })
    public DataResult<TabGiftVO> deleteGiftByIds(@PathVariable("ids")Long[] ids){
        try {
            giftService.deleteGiftByIds(ids);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("礼物接口 查询礼物信息 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据id查询礼物
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询礼物")
    public DataResult<TabGiftVO> findGiftById(@PathVariable("id")Long id){
        try {
            // 获取所有礼物信息
            TabGiftDTO dtoList = giftService.findGiftById(id);
            // 将DTO转成VO
                TabGiftVO giftVO = new TabGiftVO();
                BeanUtils.copyProperties(dtoList,giftVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(giftVO);
        }catch (Exception e){
            logger.error("礼物接口 查询礼物信息 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }


    /***
     * 增加礼物
     * @param tabGiftVO
     * @return
     */
    @PostMapping("/insertGift")
    @ApiOperation(value = "礼物增加")
    @ApiImplicitParam(name="giftVO",value = "礼物实体", required = true,
            paramType = "body", dataTypeClass = TabGiftVO.class)
    public DataResult insertGift(@RequestBody TabGiftVO tabGiftVO){
        try {
            TabGiftDTO dto = new TabGiftDTO();
            BeanUtils.copyProperties(tabGiftVO,dto);
            giftService.insertGift(dto);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("礼物接口 增加礼物 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 根据id查询礼物
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    @ApiOperation(value = "查询礼物")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "礼物ID", required = true,
                    paramType = "path", dataTypeClass = Integer.class)
    })
    public DataResult getInfoByGiftId(@PathVariable("id")Integer id){
        try {
            TabGiftDTO dTO = giftService.getInfoByGiftId(id);
            TabGiftVO vO = new TabGiftVO();
            BeanUtils.copyProperties(dTO,vO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(vO);
        }catch (Exception e){
            logger.error("礼物接口 查询异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
    /***
     * 修改标签
     * @param giftVO
     * @return
     */
    @PostMapping("/updateGiftInfo")
    @ApiOperation(value = "标签修改")
    @ApiImplicitParam(name="giftVO",value = "礼物实体", required = true,
            paramType = "body", dataTypeClass = TabGiftVO.class)
    public DataResult updateGiftInfo(@RequestBody TabGiftVO giftVO){
        try {
            TabGiftDTO dto = new TabGiftDTO();
            BeanUtils.copyProperties(giftVO,dto);
            giftService.updateGiftInfo(dto);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        }catch (Exception e){
            logger.error("标签接口 增加标签 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
}
