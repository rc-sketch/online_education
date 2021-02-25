package com.oe.service;

import com.oe.domain.dto.TabCourseTagDTO;
import com.oe.domain.dto.TabGiftDTO;
import com.oe.domain.vo.TabGiftVO;

import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service
 * @ClassName: GiftService
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 18:17
 * @Version: 1.0
 */
public interface GiftService {
    List<TabGiftDTO> findGiftList();

    TabGiftDTO findGiftById(Long id);

    void deleteGiftByIds(Long[] ids);

    void insertGift(TabGiftDTO tabGiftVO);

    TabGiftDTO getInfoByGiftId(Integer id);

    void updateGiftInfo(TabGiftDTO dto);
}
