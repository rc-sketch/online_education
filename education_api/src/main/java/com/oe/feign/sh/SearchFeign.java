package com.oe.feign.sh;

import com.oe.data.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName: online_education
 * @Package: com.oe.feign.sh
 * @ClassName: SearchFeign  搜索
 * @Author: 石晗
 * @Date: 2021/2/2 - 16:08
 * @Version: 1.0
 */
@FeignClient(value = "education-search-service")
public interface SearchFeign {
    /***
     * 查询搜索高亮显示
     * @param pageNumber        搜索课程第几页
     * @param title             搜索课程关键字
     * @return PageBean         CommentDTO的list
     */
    @PostMapping("getAll")
    DataResult getAll(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, @RequestParam String title);
}
