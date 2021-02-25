package com.oe.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/1/29 11:01
 * @Version 1.0
 **/
@Component
public class RequestRateFilter extends ZuulFilter {
    //每秒产生50个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(50);//这里设置3个令牌

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取当前线程请求上下文类
        RequestContext requestContext = RequestContext.getCurrentContext();
        //尝试获取令牌
        boolean tryAcquire = RATE_LIMITER.tryAcquire();
        // 如果获取不到就直接停止
        if(!tryAcquire){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
            HttpServletResponse response = requestContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String,Object> map = new HashMap<>();
            map.put("code",429);
            map.put("message","访问太频繁了,请稍后再试");
            try {
                response.getWriter().write(objectMapper.writeValueAsString(map));
                return map;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
