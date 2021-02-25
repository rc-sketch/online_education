package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.domain.dto.CommentDTO;
import com.oe.feign.sh.CommentFeign;
import com.oe.pojo.TabStudent;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/3 23:40
 * @Version 1.0
 **/
@Controller
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
@RequestMapping("commentController")
public class CommentController {

    @Resource
    private CommentFeign commentFeign;


//    @RequestMapping("addComment")
//    public String addComment(Long courseId, String comment,String level){
//        //获取登录用户
//        TabStudent user = (TabStudent) SecurityUtils.getSubject().getPrincipal();
//        String username = user.getName();
//        List<TbOrder> tbOrder = orderService.selectByName(username);
//        if (tbOrder == null){
//            return "login";
//        }else {
//            commentFeign.addComment(courseId,comment,level,username,tbOrder);
//        }
//        return "index";
//    }

    @RequestMapping("backMsg")
    @ResponseBody
    public String backMsg(String id, String comment){
        commentFeign.backMsg(id,comment);
        return "index";
    }

    @RequestMapping("showComment")
    @ResponseBody
    public DataResult showComment(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "3",required = false)Integer pageSize, String courseId, String like){
        DataResult dataResult = commentFeign.showComment(pageNumber,pageSize,courseId,like);
        return dataResult;
    }

    @RequestMapping("backMsgGet")
    @ResponseBody
    public DataResult backMsgGet(String id){
        DataResult dataResult = commentFeign.backMsgGet(id);
        return dataResult;
    }
}
