package com.oe.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.oe.constant.OssConstant;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabOrderDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabOrderVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.feign.yzh.OrderFeign;
import com.oe.feign.yzh.TeacherFeign;
import com.oe.pojo.TabTeacher;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-31 15:08
 **/
@RestController
@RequestMapping("courseController")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private TeacherFeign teacherFeign;
    @Autowired
    private OrderFeign orderFeign;
    /**
     * 增加课程信息表
     * @param courseVO
     * @return
     */
    @RequestMapping("/insertCourseList")
    public DataResult  insertCourseList(TabCourseVO courseVO){

        DataResult dataResult = null;
        try {
            //获取教室登录名
            TabTeacherVO teacherName = (TabTeacherVO) SecurityUtils.getSubject().getPrincipal();
            courseVO.setTeacherName(teacherName.getName());
            dataResult = teacherFeign.insertCourse(courseVO);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(dataResult.getData());
        } catch (Exception e) {
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    //上传图片
    @RequestMapping("upLoadFile")
    public DataResult upLoadFile(@RequestParam("myFile") MultipartFile myFile){
        OSS ossClient = null;
        try {

            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = OssConstant.ENDPOINT;
            // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
            String accessKeyId = OssConstant.ACCESSKEYID;
            String accessKeySecret = OssConstant.ACCESSKEYSECRET;
            String bucketName = OssConstant.BUCKETNAME;

            // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
            String objectName = myFile.getOriginalFilename();

            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
            //String content = "Hello OSS";
            String url = "";
            try {
                ossClient.putObject(bucketName, objectName,myFile.getInputStream());
                url = "http://" + bucketName + OssConstant.POINT + objectName;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(url);
        }catch (UnknownAccountException e){
            logger.error("上传失败",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    /**
     * 增加讲师课程销量统计按月统计
     *
     * @return
     */
    @PostMapping("getMonthlySales")
    public DataResult getMonthlySales(){
        try {
            logger.info("查询讲师课程销量统计");
            Subject subject = SecurityUtils.getSubject();
            TabTeacherVO teacherVO =(TabTeacherVO) subject.getPrincipal();
            //DataResult<List<TabOrderVO>> orderByTeacherName = orderFeign.findOrderByTeacherName(teacherVO.getName());
            DataResult orderByTeacherName = orderFeign.findOrderByTeacherName(teacherVO.getName());

            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(orderByTeacherName);
        }catch (Exception e){
            logger.info("失败 :{}",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
}

    