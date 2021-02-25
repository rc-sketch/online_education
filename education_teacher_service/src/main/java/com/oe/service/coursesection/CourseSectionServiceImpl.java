package com.oe.service.coursesection;

import com.oe.domain.dto.TabCourseSectionDTO;
import com.oe.mapper.course_section.CourseSectionMapper;
import com.oe.pojo.TabCourseDesc;
import com.oe.pojo.TabCourseSection;
import com.oe.service.CourseSectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-31 21:44
 **/
@Service
public class CourseSectionServiceImpl implements CourseSectionService {
    @Resource
    private CourseSectionMapper courseSectionMapper;


    @Override
    public List<Map<String, Object>> findCourseSectionList() {

        List<TabCourseSection> tabCourseSections = courseSectionMapper.selectList(null);
        List<Map<String, Object>> mapList = findCourseSectionList(tabCourseSections, 0);
        //将查询输的数据放入到redis中
        // hashOperations.put(menuKey,menuValue,mapList);
        return mapList;
    }

    private List<Map<String, Object>> findCourseSectionList(List<TabCourseSection> tabCourseSections, Integer pid) {
        //定义一个集合接收查询所有数据的结果集
        List<Map<String,Object>> mapList = new ArrayList<>();
        //for循环遍历所有数据
        for (int i = 0;i < tabCourseSections.size();i++){
            //定义一个Map集合, 来组装数据
            Map<String,Object> map = null;
            //获取menus中每个元素对象
            TabCourseSection courseSection = tabCourseSections.get(i);
            //判断当前数据室友层级关系
            if (courseSection.getParentId().equals(pid)){
                //开始组装数据
                map = new HashMap<>();
                map.put("id",courseSection.getId());
                map.put("text",courseSection.getText());
                map.put("url",courseSection.getUrl());
                map.put("nodes",findCourseSectionList(tabCourseSections,courseSection.getId().intValue()));
            }
            //判断map 不为空时 添加数据
            if (map != null){
                //把没有子节点的删掉 先获取 再删除
                List<Map<String,Object>> nodes = (List<Map<String, Object>>) map.get("nodes");
                if (nodes.size() <= 0){
                    map.remove("nodes");
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    @Override
    public void insertCourseSection(TabCourseSectionDTO tabCourseSectionDTO) {

        TabCourseSection courseSection = new TabCourseSection();
        BeanUtils.copyProperties(tabCourseSectionDTO,courseSection);
        courseSectionMapper.insert(courseSection);
    }
}

    