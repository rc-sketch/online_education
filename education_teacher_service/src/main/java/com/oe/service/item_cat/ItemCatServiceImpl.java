package com.oe.service.item_cat;

import com.oe.domain.dto.TabItemCatDTO;
import com.oe.mapper.item_cat.ItemCatMapper;
import com.oe.pojo.TabItemCat;
import com.oe.service.ItemCatService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: online_education
 * @Package: com.oe.service.item_cat
 * @ClassName: ItemCatServiceImpl
 * @Author: Y眼中人Y
 * @Date: 2021/1/30 - 20:01
 * @Version: 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private ItemCatMapper itemCatMapper;

    @Override
    public TabItemCatDTO findItemCatById(Long id) {
        TabItemCat tabItemCat = itemCatMapper.selectById(id);
        TabItemCatDTO tabItemCatDTO = new TabItemCatDTO();
        BeanUtils.copyProperties(tabItemCat,tabItemCatDTO);
        return tabItemCatDTO;
    }

    /***
     * 根据父级id查询数据
     * @param parentId
     * @return
     */
    @Override
    public List<TabItemCatDTO> findItemCatByParentId(Long parentId) {
        Map<String,Object> map = new HashMap<>();
        map.put("parent_id",parentId);
        List<TabItemCat> tabItemCats = itemCatMapper.selectByMap(map);
        List<TabItemCatDTO> list = new ArrayList<>();
        for (int i = 0; i < tabItemCats.size(); i++) {
            TabItemCatDTO tabItemCatDTO = new TabItemCatDTO();
            BeanUtils.copyProperties(tabItemCats.get(i),tabItemCatDTO);
            list.add(tabItemCatDTO);
        }
        return list;
    }
}
