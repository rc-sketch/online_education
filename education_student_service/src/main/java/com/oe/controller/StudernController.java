package com.oe.controller;

import com.oe.constant.PhoneCodeConstant;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.dto.TabStudentDTO;
import com.oe.domain.dto.TabTeacherDTO;
import com.oe.domain.vo.TabCourseVO;
import com.oe.domain.vo.TabStudentVO;
import com.oe.domain.vo.TabTeacherVO;
import com.oe.pojo.TabStudent;
import com.oe.pojo.TabTeacher;
import com.oe.service.StudentService;
import com.oe.util.PageBean;
import com.oe.util.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @ClassName StudernController
 * @Author RC
 * @date 2021.02.01 01:13
 */
@RestController
@RequestMapping("student")
@RefreshScope
//开启自动刷新配置  nacos的配置文件自动刷新
@Api(value = "学生接口")
public class StudernController {

    @Resource
    private StudentService studentService;

    @Resource
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(StudernController.class);
    /***
     * 根据主键查询对应学生信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键查询学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学生id", required = true,
                    paramType = "path", dataTypeClass = Long.class),
    })
    public DataResult<TabStudentVO> findStudentById(@PathVariable("id") Long id){
        try {
            // 根据id查询
            TabStudentDTO StudentById = studentService.findStudentById(id);
            // 创建VO实体
            TabStudentVO student= new TabStudentVO();
            // 将DTO转成VO
            BeanUtils.copyProperties(StudentById,student);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(student);
        }catch (Exception e){
            logger.error("学生接口 根据主键获取教室信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }

    /***
     * 查询学生信息并分页
     * @return
     */
    @GetMapping("/findStudentPage")
    @ApiOperation(value = "查询学生信息并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数", required = true,
                    dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true,
                    dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "学生名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "phone", value = "学生手机号", dataTypeClass = String.class),
    })
    public DataResult<TabStudentVO> findStudentPage(@RequestParam(defaultValue = "1")Integer pageNumber, @RequestParam(defaultValue = "3")Integer pageSize, String name, String phone){
        try {
            PageBean page = studentService.findStudentPage(pageNumber,pageSize,name,phone);
            List<TabStudentDTO> result = page.getResult();

            List<TabStudentVO> list = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                TabStudentVO tabStudentVO = new TabStudentVO();
                BeanUtils.copyProperties(result.get(i),tabStudentVO);
                list.add(tabStudentVO);
            }
            page.setResult(list);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(page);
        }catch (Exception e){
            logger.error("学生接口 根据主键获取学生信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
    /***************************************************************************************************************************
     * 注册方法
     * @tabTeacherVO
     * @return
     */
    @PostMapping("/insertRegister")
    @ApiOperation(value = "注册用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "TabStudentVO", value = "学生对象",
                    dataTypeClass = TabCourseVO.class),
            @ApiImplicitParam(name = "newpassword", value = "确认密码",
                    dataTypeClass = String.class),
            @ApiImplicitParam(name = "code", value = "验证码",
                    dataTypeClass = String.class),

    })
    public DataResult insertRegister(@RequestBody TabStudentVO studentVO,@RequestParam("newpassword") String newpassword,@RequestParam("code") String code) {

        String salt = null;
        try {
            //获取加盐次数
            salt = ShiroUtils.generateSalt(6);
            TabStudentDTO studentDTO = new TabStudentDTO();
            BeanUtils.copyProperties(studentVO, studentDTO);
            studentDTO.setSalt(salt);
            RedisSerializer stringSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(stringSerializer);
            redisTemplate.setValueSerializer(stringSerializer);
            //使用MD5加密
            String md5 = ShiroUtils.encryptPassword("MD5", studentDTO.getPassword(), salt, 3);
            if (StringUtils.isBlank(studentDTO.getName())) {
                logger.error("注册失败 用户名不可为空");
                return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
            }
            if (StringUtils.isBlank(studentDTO.getPassword())) {
                logger.error("注册失败 密码不可为空");
                return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
            }
            if (!newpassword.equals(studentDTO.getPassword()) && StringUtils.isBlank(studentVO.getPassword())) {
                logger.error("注册失败 两次密码不一致");
                return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
            }
            if (StringUtils.isBlank(studentDTO.getPhone())) {
                logger.error("注册失败 手机号不可为空");
                return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
            }
            //从缓存中获取验证码
            String code1 = (String) redisTemplate.opsForValue().get(PhoneCodeConstant.PHONE_CODE + studentDTO.getPhone());
            if (!code1.equals(code)) {
//                logger.error("注册失败 验证码不正确,");
                return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
            }

            studentDTO.setPassword(md5);
            studentDTO.setSalt(salt);
            studentDTO.setCreated(new Date());
            studentDTO.setUpdated(new Date());
            studentDTO.setBirthday(new Date());
            studentDTO.setLastLoginTime(new Date());

            studentService.insertRegister(studentDTO);
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.error("注册失败", e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * 根据教师名称查询
     * @return
     */
    @GetMapping("/findStudentByName/{name}")
    @ApiOperation(value = "根据教师名称查询(用户认证)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = true,
                    paramType = "path",
                    dataTypeClass = String.class),
    })
    public DataResult<TabStudentVO> findStudentByName(@PathVariable("name") String name){
        try {
            TabStudentDTO studentDTO = studentService.findStudentByName(name);
            TabStudent student = new TabStudent();
            BeanUtils.copyProperties(studentDTO,student);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(student);
        }catch (Exception e){
            logger.error("学生接口,获取name信息异常 :{}",e);
            return DataResult.response(ResponseStatusEnum.BAD_REQUEST);
        }
    }
}
