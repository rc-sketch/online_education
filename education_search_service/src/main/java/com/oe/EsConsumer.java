package com.oe;

import com.alibaba.fastjson.JSON;
import com.oe.data.DataResult;
import com.oe.domain.vo.TabCourseVO;
import com.oe.feign.yzh.TeacherFeign;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description
 * @ClassName EsConsumer
 * @Author RC
 * @date 2021.02.17 16:16
 */
@Component
@RocketMQMessageListener(topic = "approvedCourseSendSuccess",messageModel = MessageModel.BROADCASTING,consumerGroup = "education_admin_web_producer")
public class EsConsumer implements RocketMQListener<Long[]> {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private TeacherFeign teacherFeign;

    private static final Logger logger = LoggerFactory.getLogger(EsConsumer.class);

    @Override
    public void onMessage(Long[] courseId) {
        for (Long id : courseId) {
            DataResult<TabCourseVO> course = teacherFeign.findCourseById(id);
            TabCourseVO data = course.getData();
            try {
                IndexRequest indexRequest = new IndexRequest("tab_course").source(JSON.toJSONString(data), XContentType.JSON);
                // 执行得到 response
                IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
                logger.info("出参：" + indexResponse.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
