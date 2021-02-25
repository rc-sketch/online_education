package com.oe.controller;

import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.feign.sh.SearchFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/2 21:51
 * @Version 1.0
 **/
@Controller
@RequestMapping("searchController")
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);


    @Resource
    private SearchFeign searchFeign;

    /**
     * 注册
     * @param pageNumber
     * @param title
     * @return
     */
    @RequestMapping("getAll")
    @ResponseBody
    public DataResult insertRegister(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, String title){

        try {
            logger.debug("进入>>>>>getAll,参数为 {},{}",pageNumber,title);
            DataResult searchFeignAll = searchFeign.getAll(pageNumber, title);
            return searchFeignAll;
        } catch (Exception e) {
            logger.error("getAll",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
