package com.oe.datasource.log;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: dongdongshop_parent
 * @description:
 * @author: zh
 * @create: 2020-12-09 19:30
 */
//使用springAOP打印日志
//通知类
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //切点表达式方法
    @Pointcut("execution(public * com.oe.service..*.*(..))")
    public void aoplog(){
    }

    //定义前置通知
    @Before("aoplog()")//方法执行前执行
    public  void doBefore(JoinPoint joinPoint){
        //获取参数
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        logger.info("正在执行方法:{},参数为{}",signature,args);
    }

    //returning表示返回的结果
    @AfterReturning(pointcut = "aoplog()",returning = "result")//方法执行后执行
    public void doAfterReturn(Object result) throws Throwable{
        //打印返回的结果
        logger.info("方法执行成功，返回结果为:{}", JSONObject.toJSONString(result));
    }
}