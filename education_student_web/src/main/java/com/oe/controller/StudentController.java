package com.oe.controller;

import com.oe.constant.PhoneCodeConstant;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.domain.vo.TabStudentVO;
import com.oe.feign.rc.StudentFeign;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-02-01 12:30
 **/
@Controller
@RequestMapping("studentController")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private StudentFeign studentFeign;
    /**
     * 登录方法
     * @param name
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("login")
    public String login(String name, String password, Model model){
        //获取subject主体
        Subject subject = SecurityUtils.getSubject();
        TabStudentVO user  = (TabStudentVO) subject.getPrincipal();
        if (user != null){
            studentFeign.findStudentByName(user.getName());
        }
        //关联Realm 数据库
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        try {
            subject.login(usernamePasswordToken);
            model.addAttribute("user", name);
            return "redirect:/index";
        }catch (UnknownAccountException u){
            //System.out.println("账号错误");
            model.addAttribute("message","账号错误");
            return "redirect:/login";
        }catch (IncorrectCredentialsException i){
            //System.out.println("密码错误");
            model.addAttribute("meaasge","密码错误");
            return "redirect:/login";
        }
    }

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @RequestMapping("sendCode")
    @ResponseBody
    public DataResult sendCode(String phone){
        try {
            //创建验证码随机数
            String code = RandomStringUtils.randomNumeric(4);
            //将手机号和验证码发送给mq
            String phoneCode = "" +phone +","+ code + "";
            rocketMQTemplate.convertAndSend("sms_phone_code", phoneCode);
            //序列化
            RedisSerializer stringSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(stringSerializer);
            redisTemplate.setValueSerializer(stringSerializer);
            // 将code保存到redis中
            redisTemplate.opsForValue().set(PhoneCodeConstant.PHONE_CODE + phone, code);
            //设置过期时间
            redisTemplate.expire(PhoneCodeConstant.PHONE_CODE + phone, 1, TimeUnit.HOURS);
            logger.info("验证码发送成功 参数为:{}");
            return DataResult.response(ResponseStatusEnum.SUCCESS);
        } catch (Exception e) {
            logger.info("验证码发送失败 参数为:{}", e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 注册
     * @param studentVO
     * @param newpassword
     * @param code
     * @return
     */
    @RequestMapping("insertRegister")
    @ResponseBody
    public DataResult insertRegister(TabStudentVO studentVO, String newpassword, String code){

        try {
            DataResult dataResult = studentFeign.insertRegister(studentVO, newpassword, code);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(dataResult.getCode());
        } catch (Exception e) {
            logger.error("注册失败",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }


}

    