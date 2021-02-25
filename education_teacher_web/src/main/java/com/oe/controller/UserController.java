package com.oe.controller;

import com.oe.constant.AdminLoginConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.TimeUnit;

/**
 * @program: online_education
 * @description:
 * @author: 邢孟君
 * @create: 2021-01-30 17:46
 **/
@Controller
@RequestMapping("userController")
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;
    /*
     * @String username 用户名
     * @String password 密码
     * */
    @RequestMapping("login")
    public String login(String name, String password, Model model){
        //判断用户和密码是否为空
        if (null == name || "".equals(name)
                || null == password || "".equals(password)){
            model.addAttribute("message","用户或者密码不能为空");
        }

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token =new UsernamePasswordToken(name,password);
        //错误次数
        String adminLoginErrorCount =  AdminLoginConstant.ADMIN_LOGIN_ERROR_COUNT + name;
        //锁定账号
        String adminLoginErrorLock = AdminLoginConstant.ADMIN_LOGIN_ERROR_LOCK + name;
        //判断该账号是否被锁定 是 就提示用户在多少分钟在登录
        if (redisTemplate.hasKey(adminLoginErrorLock)){
            model.addAttribute("message","由于密码错误次数已达上限，请在"+redisTemplate.getExpire(adminLoginErrorLock, TimeUnit.MINUTES)+"分钟后再试");
            return "/shoplogin";
        }

        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户未注册");
            return "/shoplogin";
        }catch (IncorrectCredentialsException e){
            //判断错误次数是否是第一次 或超过我们的需求
            if (!redisTemplate.hasKey(adminLoginErrorCount)){
                //没有 表示第一次失败 错误次数并赋值加一
                redisTemplate.opsForValue().set(adminLoginErrorCount,1);
                //提醒用户在多少分钟内还有几次错误机会
                redisTemplate.expire(adminLoginErrorCount,2, TimeUnit.MINUTES);
                model.addAttribute("message","在120内还可以输入四次密码");
                return "/shoplogin";
            }else {
                //表示不是第一次失败了 获取失败次数
                Long count  = Long.parseLong( redisTemplate.opsForValue().get(adminLoginErrorCount).toString());
                //判断错误是否次数小于4次
                if (count < 4){
                    //小于 就错误次数加一次 并提示用户还有几次错户机会
                    Long increment = redisTemplate.opsForValue().increment(adminLoginErrorCount, 1);
                    Long expire = redisTemplate.getExpire(adminLoginErrorCount, TimeUnit.SECONDS);
                    model.addAttribute("message","在"+expire+"秒内，还可以输入"+(5 - increment)+"次密码");
                    return "/shoplogin";
                }else {
                    //不小于 就提示用户该账号已被冻结,在多1时后登陆
                    redisTemplate.opsForValue().set(adminLoginErrorLock,"lock");
                    redisTemplate.expire(adminLoginErrorLock,1, TimeUnit.HOURS);
                    model.addAttribute("message","该账号已被冻结，请站在一小时后再试");
                    return "/shoplogin";
                }
            }
        }
        //登陆成功就情书错误次数
        redisTemplate.delete(adminLoginErrorCount);
//        model.addAttribute("message","登录成功");

        return "redirect:/toIndex";
    }
}

    