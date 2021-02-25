package com.oe.controller;

import com.alibaba.fastjson.JSON;
import com.oe.data.DataResult;
import com.oe.data.ResponseStatusEnum;
import com.oe.pojo.TabCourse;
import com.oe.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2021/2/2 15:17
 * @Version 1.0
 **/
@RestController
@Api(value = "搜索接口")
@RefreshScope
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Resource
    private RestHighLevelClient restHighLevelClient;

    //获取某个索引下所有数据
    @ApiOperation(value = "查询es搜索信息并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "title", value = "查询搜索关键字", dataTypeClass = String.class),
    })
    @PostMapping("getAll")
    public DataResult getAll(@RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber, @RequestParam String title) {
        try {
            logger.debug("进入>>>>>getAll,参数为 {},{}",pageNumber,title);
            PageBean pageBean = new PageBean();
            // 搜索请求对象
            SearchRequest searchRequest = new SearchRequest("tab_course");
            // 搜索源构建对象
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            int page = 1; // 页码
            int size = 10; // 每页显示的条数
            int index = (pageNumber - 1) * size;
            searchSourceBuilder.from(index);
            searchSourceBuilder.size(size);
            // 搜索方式
            // Query-match单字段模糊查询
            searchSourceBuilder.query(QueryBuilders.matchQuery("title", title));
            //模糊查询
//        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "title");
            //单字段精确查询
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //添加过滤条件
//        TermQueryBuilder status = QueryBuilders.termQuery("status", "1");
//        boolQueryBuilder.must(status);
//        boolQueryBuilder.should(matchQueryBuilder);
            // matchAllQuery搜索全部
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            // 设置源字段过虑,第一个参数结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
//        searchSourceBuilder.fetchSource(new String[]{"title","price","image", "spec", "seller"}, new String[]{});
            // 向搜索请求对象中设置搜索源
            searchRequest.source(searchSourceBuilder);
            //高亮显示
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            //设置高亮前缀
            highlightBuilder.preTags("<span style='color:red'>");
            //设置高亮字段
            highlightBuilder.field("title");
            //设置高亮后缀
            highlightBuilder.postTags("</span>");
            searchSourceBuilder.highlighter(highlightBuilder);
            // 执行搜索,向ES发起http请求
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            // 搜索结果
            SearchHits hits = searchResponse.getHits();
            List jsonList = new ArrayList();
            logger.debug("进入>>>>>getAll,jsonList的参数为 {}",jsonList);
            for (SearchHit hit : hits.getHits()) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String titleName = (String) sourceAsMap.get("title");
                TabCourse course = JSON.parseObject(JSON.toJSONString(sourceAsMap), TabCourse.class);
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                if( highlightFields != null ){
                    HighlightField nameField = highlightFields.get("title");
                    if(nameField!=null){
                        Text[] fragments = nameField.getFragments();
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Text str : fragments) {
                            stringBuilder.append(str.string());
                        }
                        titleName = stringBuilder.toString();
                        course.setTitle(titleName);
                    }
                }
                jsonList.add(course);
            }
            pageBean.setResult(jsonList);
            pageBean.setPageSize(size);
            pageBean.setPageNumber(pageNumber);
            logger.debug("结束>>>>>getAll,返回参数为: {}",pageBean);
            return DataResult.response(ResponseStatusEnum.SUCCESS).setData(pageBean);
        }catch (Exception e){
            logger.error("getAll",e);
            return DataResult.response(ResponseStatusEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
