package com.oe.service.teacher_gift;

import com.oe.domain.dto.TabTeacherGiftDTO;
import com.oe.mapper.teacher_gift.TeacherGiftMapper;
import com.oe.pojo.TabTeacherGift;
import com.oe.service.TeacherGiftService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service.teacher_gift
 * @ClassName: TeacherGiftServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/1/31 - 19:29
 * @Version: 1.0
 */
@Service
public class TeacherGiftServiceImpl implements TeacherGiftService {

    @Resource
    private TeacherGiftMapper teacherGiftMapper;

    /***
     * 查询教师收到的礼物
     * @return
     */
    @Override
    public List<TabTeacherGiftDTO> findTeacherGiftList() {
        List<TabTeacherGift> tabTeacherGifts = teacherGiftMapper.selectList(null);
        List<TabTeacherGiftDTO> dtoList = new ArrayList<>();
        tabTeacherGifts.forEach(gift ->{
            TabTeacherGiftDTO dto = new TabTeacherGiftDTO();
            BeanUtils.copyProperties(gift,dto);
            dtoList.add(dto);
        });
        return dtoList;
    }
}
