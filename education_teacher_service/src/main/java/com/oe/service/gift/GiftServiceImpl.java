package com.oe.service.gift;

import com.oe.domain.dto.TabCourseTagDTO;
import com.oe.domain.dto.TabGiftDTO;
import com.oe.domain.vo.TabGiftVO;
import com.oe.mapper.gift.GiftMapper;
import com.oe.pojo.TabCourseTag;
import com.oe.pojo.TabGift;
import com.oe.service.GiftService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service.gift
 * @ClassName: GiftServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 18:17
 * @Version: 1.0
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Resource
    private GiftMapper giftMapper;

    @Override
    public void insertGift(TabGiftDTO dto) {
        TabGift gift = new TabGift();
        BeanUtils.copyProperties(dto,gift);
        giftMapper.insert(gift);
    }

    /***
     * 根据id删除礼物
     * @param ids
     */
    @Override
    public void deleteGiftByIds(Long[] ids) {
        giftMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /***
     * 根据id查询礼物信息
     * @param id
     * @return
     */
    @Override
    public TabGiftDTO findGiftById(Long id) {
        TabGift tabGift = giftMapper.selectById(id);
        TabGiftDTO tabGiftDTO = new TabGiftDTO();
        BeanUtils.copyProperties(tabGift,tabGiftDTO);
        return tabGiftDTO;
    }

    /***
     * 查询所有礼物
     * @return
     */
    @Override
    public List<TabGiftDTO> findGiftList() {
        List<TabGift> tabGifts = giftMapper.selectList(null);
        List<TabGiftDTO> dtoList = new ArrayList<>();
        tabGifts.forEach((pojo) -> {
            TabGiftDTO dto = new TabGiftDTO();
            BeanUtils.copyProperties(pojo,dto);
            dtoList.add(dto);
        });
        return dtoList;
    }
    /***
     * 根据id查询礼物信息
     * @param id
     * @return
     */
    @Override
    public TabGiftDTO getInfoByGiftId(Integer id) {
        TabGift tabGift = giftMapper.selectById(id);
        TabGiftDTO tabGiftDTO = new TabGiftDTO();
        BeanUtils.copyProperties(tabGift,tabGiftDTO);
        return tabGiftDTO;
    }

    /***
     * 修改礼物
     * @param dto
     */
    @Override
    public void updateGiftInfo(TabGiftDTO dto) {
        TabGift pojo = new TabGift();
        BeanUtils.copyProperties(dto,pojo);
        giftMapper.updateById(pojo);
    }
}
