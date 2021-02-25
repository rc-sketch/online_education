package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.CommentDTO;
import com.oe.pojo.TabOrder;
import com.oe.pojo.TabStudent;
import com.oe.service.CommentService;
import com.oe.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/1 22:02
 * @Version 1.0
 **/
@RestController
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
@RefreshScope//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "评论接口")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Resource
    private CommentService commentService;
    @Resource
    private MongoTemplate mongoTemplate;
//    @Resource
//    private OrderFeign orderFeign;//todo 调订单服务
//
//    @ApiOperation(value = "增加评论信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "课程id",name = "courseId",required = true, dataTypeClass = String.class),
//            @ApiImplicitParam(value = "回复评论信息", name = "comment", required = true, dataTypeClass = String.class),
//            @ApiImplicitParam(value = "评论等级(星星)", name = "level", required = true, dataTypeClass = String.class),
//    })
//    @PostMapping("addComment")
//    public DataResult addComment(@RequestParam("courseId")Long courseId, @RequestParam("comment")String comment, @RequestParam("level")String level){
//        try{
//            logger.debug("进入>>>>>addComment,参数为 {},{},{}",courseId,comment,level);
//            //获取登录用户
//            TabStudent student = (TabStudent) SecurityUtils.getSubject().getPrincipal();
//            String name = student.getName();
//            List<TabOrder> tbOrder = orderFeign.selectByName(name);//todo 调订单服务
//            if (tbOrder == null){
//                logger.debug("结束>>>>>addComment,返回结果\"没资格评论\"");
//                return DataResult.response(ResponseStatusEnum.SUCCESS).setData("没资格评论");
//            }else {
//                commentService.addComment(courseId,comment,level,name,tbOrder);
//            }
//            logger.debug("结束>>>>>addComment,返回结果\"评论成功\"");
//            return DataResult.response(ResponseStatusEnum.SUCCESS).setData("评论成功");
//        }catch (Exception e){
//            logger.error("addComment",e);
//            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    @ApiOperation(value = "增加评论回复信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "MongoDB学生评论id",name = "id",required = true, dataTypeClass = String.class),
//            @ApiImplicitParam(value = "回复评论信息", name = "comment", required = true, dataTypeClass = String.class),
//    })
//    @PostMapping("backMsg")
//    public DataResult backMsg(@RequestParam("id")String id, @RequestParam("comment")String comment){
//        try{
//            logger.debug("进入>>>>>backMsg,参数为 {},{}",id,comment);
//            commentService.backMsg(id,comment);
//            logger.debug("结束>>>>>backMsg,返回结果\"评论成功\"");
//            return DataResult.response(ResponseStatusEnum.SUCCESS).setData("评论回复成功");
//        }catch (Exception e){
//            logger.error("backMsg",e);
//            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
//        }
//    }

    @ApiOperation(value = "查询评论信息并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "courseId", value = "教师名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "like", value = "评论等级(星星)", dataTypeClass = String.class),
    })
    @GetMapping("showComment")
    public DataResult showComment(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "3",required = false)Integer pageSize,String courseId,String like){
        try{
            logger.debug("进入>>>>>showComment,参数为 {},{},{},{}",pageNumber,pageSize,courseId,like);
            PageBean<CommentDTO> pageBean = new PageBean<>();
            pageBean.setPageNumber(pageNumber);
            pageBean.setPageSize(pageSize);
            //条件查询
            Query query = null;
            if (like == null){
                query = new Query(Criteria.where("courseId").is(courseId));
            }else {
                query = new Query(Criteria.where("courseId").is(courseId).and("level").is(like));
            }
            //查询总条数
            long count = mongoTemplate.count(query, CommentDTO.class);
            pageBean.setTotalCount(Integer.parseInt(String.valueOf(count)));
            //添加分页功能
            query.skip((pageNumber - 1) * pageSize).limit(pageSize);
            //查询分页数据
            List<CommentDTO> list = mongoTemplate.find(query, CommentDTO.class);
            //如果我们想要将总条数这些数据也返回的话，自己封装一下Page工具类即可
            pageBean.setResult(list);
            logger.debug("结束>>>>>showComment,返回参数为: {}",pageBean);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(pageBean);
        }catch (Exception e){
            logger.error("showComment",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "展示评论回复信息")
    @ApiImplicitParam(value = "MongoDB学生评论id",name = "id",required = true,paramType = "path")
    @GetMapping("backMsgGet/{id}")
    public DataResult backMsgGet(@PathVariable("id") String id){
        try{
            logger.debug("进入>>>>>backMsgGet,参数为 {}",id);
            //条件查询
            Query query = null;
            query = new Query(Criteria.where("pid").is(id));
            //查询总条数
            long count = mongoTemplate.count(query, CommentDTO.class);
            //添加分页功能
//        query.skip((pageNumber - 1) * pageSize).limit(pageSize);
            //查询分页数据
            List<CommentDTO> list = mongoTemplate.find(query, CommentDTO.class);
            //如果我们想要将总条数这些数据也返回的话，自己封装一下Page工具类即可
            logger.debug("结束>>>>>backMsgGet,返回参数为: {}",list);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(list);
        }catch (Exception e){
            logger.error("backMsgGet",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }

    }

}
