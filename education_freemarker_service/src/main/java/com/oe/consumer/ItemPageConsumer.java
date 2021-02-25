package com.oe.consumer;

import com.oe.data.DataResult;
import com.oe.domain.vo.TabCourseDescVO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.service.FreemarkerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @ClassName ItemPageConsumer
 * @Author RC
 * @date 2020.12.18 11:52
 */
@Component
@RocketMQMessageListener(topic = "approvedCourseSendSuccess",messageModel = MessageModel.BROADCASTING,consumerGroup = "education_admin_web_producer")
public class ItemPageConsumer implements RocketMQListener<Long[]> {

    @Autowired
    private FreemarkerService freemarkerService;
    private static final Logger logger = LoggerFactory.getLogger(ItemPageConsumer.class);

    @Autowired
    private TeacherFeign teacherFeign;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${freemarker.outDir}")
    private String outDir;

    @Override
    public void onMessage(Long[] longs) {
        for (int i = 0; i < longs.length; i++) {
//            freemarkerService.createItemPage(longs[i]);
            //获取Configuration对象
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            FileWriter fileWriter = null;
            try {
                //获取模板Template对象
                Template template = configuration.getTemplate("item.ftl");
                System.out.println("生成静态页面");
                //根据课程ID查询课程表信息
                DataResult<TabCourseVO> dataResult = teacherFeign.findCourseById(longs[i]);
                TabCourseVO course = dataResult.getData();
                //根据课程ID查询课程详情表信息
                DataResult<TabCourseDescVO> dataResult1 = teacherFeign.findCourseDescByCourseId(longs[i]);
                TabCourseDescVO courseDescVO = dataResult1.getData();
                //根据课程表中教师id查询教师表详情信息(静态页教师介绍展示)
                DataResult<TabTeacherVO> dataResult2 = teacherFeign.findTeacherByName(course.getTeacherName());
                TabTeacherVO teacherVO = dataResult2.getData();
//
//           //导航栏
//            TbItemCat item1 = itemCatService.findItemByGoodsCagetoryId(goods.getCategory1Id());
//            TbItemCat item2 = itemCatService.findItemByGoodsCagetoryId(goods.getCategory2Id());
//            TbItemCat item3 = itemCatService.findItemByGoodsCagetoryId(goods.getCategory3Id());
                //创建数据模型
                Map map = new HashMap();
                map.put("course", course);
                map.put("courseDescVO", courseDescVO);
                map.put("teacherVO", teacherVO);
//            map.put("itemList",itemList);
//            map.put("item1",item1);
//            map.put("item2",item2);
//            map.put("item3",item3);

                //创建输出对象Writer
                fileWriter = new FileWriter(outDir + longs[i] + ".html");
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
}
