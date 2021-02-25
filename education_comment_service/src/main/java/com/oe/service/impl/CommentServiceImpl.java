package com.oe.service.impl;

import com.oe.data.DataResult;
import com.oe.domain.dto.CommentDTO;
import com.oe.domain.dto.TabCourseDTO;
import com.oe.domain.dto.TabOrderItemDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.pojo.TabCourse;
import com.oe.pojo.TabOrder;
import com.oe.service.CommentService;
import com.oe.util.IdWorker;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 22:01
 * @Version 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private MongoTemplate mongoTemplate;
//    @Resource
//    private OrderItemFeign orderItemFeign;//todo 调订单服务
    @Resource
//    private CourseFeign courseFeign;//todo 调订单服务
    private TeacherFeign teacherFeign;//todo 调订单服务

//    @Override
//    public void addComment(Long courseId, String comment, String level, String studentName, List<TabOrder> TabOrderList) {
//        CommentDTO commentDB = new CommentDTO();
//        IdWorker worker = new IdWorker();
//        TabOrder order = TabOrderList.get(0);
//        Long orderId = order.getOrderId();
//        TabOrderItemDTO orderItem = orderItemFeign.selectByOrderId(orderId);
//        Long itemId1 = orderItem.getId();
//        if (order.getStatus().equals("2") && courseId.equals(itemId1)){//    2已付款
//            long l = worker.nextId();
//            commentDB.setId(String.valueOf(l));
//            commentDB.setComment(comment);
//            commentDB.setCreateTime(new Date());
//            commentDB.setCourseId(courseId.toString());
//            commentDB.setLevel(level);
//            commentDB.setPid("0");
//            commentDB.setStudentName(studentName);
//            DataResult<TabCourseVO> courseVODataResult = teacherFeign.findCourseById(courseId);
//            TabCourseVO tabCourse = courseVODataResult.getData();
//            commentDB.setTitle(tabCourse.getTitle());
//            mongoTemplate.insert(commentDB);//必须指定id
//        }
//    }
//
//    @Override
//    public void backMsg(String id, String comment) {
//        CommentDTO commentDB = new CommentDTO();
//        IdWorker worker = new IdWorker();
//        long lid = worker.nextId();
//        commentDB.setId(String.valueOf(lid));
//        commentDB.setComment(comment);
//        commentDB.setCreateTime(new Date());
//        commentDB.setPid(id);
//        mongoTemplate.insert(commentDB);//必须指定id
//    }
}
