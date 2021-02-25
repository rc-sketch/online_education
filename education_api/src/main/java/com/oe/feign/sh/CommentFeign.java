package com.oe.feign.sh;

import com.oe.data.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName: online_education
 * @Package: com.oe.feign.sh
 * @ClassName: CommentFeign  评论
 * @Author: 石晗
 * @Date: 2021/2/2 - 09:08
 * @Version: 1.0
 */
@FeignClient(value = "education-comment-service")
public interface CommentFeign {

    /***
     * 增加商品评论
     * @param courseId      秒杀商品的courseId
     * @param comment       秒杀商品的评论内容
     * @param level         秒杀商品的评级(星星)
     * @return String       弹出框的内容
     */
    @PostMapping("addComment")
    DataResult addComment(@RequestParam("courseId")Long courseId, @RequestParam("comment")String comment, @RequestParam("level")String level);

    /***
     * 增加商品评论回复
     * @param id            评论id
     * @param comment       秒杀商品的评论回复内容
     * @return  String      弹出框的内容
     */
    @PostMapping("backMsg")
    DataResult backMsg(@RequestParam("id") String id, @RequestParam("comment") String comment);

    /***
     * 查询全部秒杀商品(分页)
     * @param pageNumber     秒杀商品第几页
     * @param pageSize       秒杀商品每页展示几条
     * @param courseId       秒杀商品的courseId
     * @param like           秒杀商品的评级(星星)
     * @return PageBean      CommentDTO的list
     */
    @GetMapping("showComment")
    DataResult showComment(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "3",required = false)Integer pageSize, @RequestParam  String courseId,@RequestParam String like);


    /***
     * 查询全部秒杀商品的回复评论
     * @param id            评论id
     * @return  list        List<CommentDTO>
     */
    @GetMapping("backMsgGet/{id}")
    DataResult backMsgGet(@PathVariable("id") String id);
}
