package com.oe.feign.rc;

import com.oe.data.DataResult;
import com.oe.domain.vo.TabStudentVO;
import com.oe.pojo.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "education-cart-service")
public interface CartFeign {

    /***
     * 根据主键查询学生信息
     * @param courseId
     * @return
     */
    @PostMapping("cart/addCart/{courseId}")
    DataResult addCart(@PathVariable("courseId") Long courseId,TabStudentVO user);

    /***
     * 根据购物车信息
     * @param
     * @return
     */
    @PostMapping("cart/cartList")
    String  cartList(@RequestBody TabStudentVO user);
}
