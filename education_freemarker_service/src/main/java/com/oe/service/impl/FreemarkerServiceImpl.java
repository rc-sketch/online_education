package com.oe.service.impl;

import com.oe.data.DataResult;
import com.oe.domain.vo.TabCourseVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.service.FreemarkerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName FreemarkerServiceImpl
 * @Author RC
 * @date 2020.12.17 18:56
 */
@Service
public class FreemarkerServiceImpl implements FreemarkerService {

    @Autowired
    private TeacherFeign teacherFeign;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${freemarker.outDir}")
    private String outDir;

    @Override
    public void createItemPage(Long id) {
        //获取Configuration对象
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        FileWriter fileWriter = null;
        try {
            //获取模板Template对象
            Template template = configuration.getTemplate("item.ftl");
            System.out.println("生成静态页面");
            //根据课程ID查询课程表信息
//            DataResult<TabCourseVO> dataResult = teacherFeign.findCourseById(id);
//            TabCourseVO course = dataResult.getData();
            //根据课程ID查询课程表详情信息

//            TbGoodsDesc goodsDesc = t.findItemCatById(id);
//           List<TbItem> itemList = itemService.findItemById(goodsId);
//
//           //导航栏
//            TbItemCat item1 = itemCatService.findItemByGoodsCagetoryId(goods.getCategory1Id());
//            TbItemCat item2 = itemCatService.findItemByGoodsCagetoryId(goods.getCategory2Id());
//            TbItemCat item3 = itemCatService.findItemByGoodsCagetoryId(goods.getCategory3Id());
            //创建数据模型
            Map map = new HashMap();
//            map.put("course",course);
//            map.put("goodsDesc",goodsDesc);
//            map.put("itemList",itemList);
//            map.put("item1",item1);
//            map.put("item2",item2);
//            map.put("item3",item3);

            //创建输出对象Writer
            fileWriter = new FileWriter(outDir + id + ".html");
            template.process(map,fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileWriter != null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
