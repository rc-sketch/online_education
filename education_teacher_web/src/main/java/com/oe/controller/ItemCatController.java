package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.feign.yzh.TeacherFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-31 15:28
 **/
@RestController
@RequestMapping("itemCatController")
public class ItemCatController {

    @Autowired
    private TeacherFeign teacherFeign;

    /**
     * 根据父级id查询一级分类
     * @param parentId
     * @return
     */
    @RequestMapping("findItemCatByParentId/{parentId}")
    public DataResult findItemCatByParentId(@PathVariable("parentId")Long parentId){

        try {
            DataResult itemCatByParentId = teacherFeign.findItemCatByParentId(parentId);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(itemCatByParentId.getData());
        } catch (Exception e) {
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

}

    