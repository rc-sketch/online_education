package com.oe.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/1/29 11:44
 * @Version 1.0
 **/
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 配置过滤类型，有四种不同生命周期的过滤器类型
     * 1. pre：路由之前
     * 2. routing：路由之时
     * 3. post：路由之后
     * 4. error：发送错误调用
     * @return
     */

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RequestRateFilter requestRateFilter;

    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        //这里可以加判断条件,当前一个过滤器失败时,这里设置为false
        try {
            if (requestRateFilter.run() != null){
                return false;
            }
        } catch (ZuulException e) {
            logger.error("shouldFilter",e);
        }
        return true;
    }
    @Override
    public Object run() throws ZuulException {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //获取当前线程请求上下文类
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request域对象
        HttpServletRequest request = currentContext.getRequest();

        //从头部获取登录token
        String token = request.getHeader("AuthToken");

        //校验用户名是否存在，如果不存在，说明用户未登录，不放过
        if(token != null && !"".equals(token) && redisTemplate.boundValueOps(token).get() != null){
            return null;
        }else {
            //设置响应域对象
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            //获取response
            HttpServletResponse response = currentContext.getResponse();
            try {
                response.setContentType("application/json;charset=utf-8");
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String,Object> map = new HashMap<>();
                map.put("code",401);
                map.put("message","用户未登录，无法访问服务,请先登录");
                response.getWriter().write(objectMapper.writeValueAsString(map));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
