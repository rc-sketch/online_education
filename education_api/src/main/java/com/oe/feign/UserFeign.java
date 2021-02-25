package com.oe.feign;


import com.oe.data.DataResult;
import com.oe.domain.dto.UserDTO;
import com.oe.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",fallback = UserFallback.class)
public interface UserFeign {
    @GetMapping("/userController/{id}")
    DataResult<UserDTO> getOne(@PathVariable("id") Integer id);
}
