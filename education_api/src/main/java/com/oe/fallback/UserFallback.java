package com.oe.fallback;


import com.oe.data.DataResult;
import com.oe.domain.dto.UserDTO;
import com.oe.feign.UserFeign;
import org.springframework.stereotype.Component;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/1/20 15:36
 * @Version 1.0
 **/
@Component
public class UserFallback implements UserFeign {
    @Override
    public DataResult<UserDTO> getOne(Integer id) {
        //方法里面写降级处理功能   eg: 打印日志等
        return new DataResult<>(500,"fallback",null);
    }
}
