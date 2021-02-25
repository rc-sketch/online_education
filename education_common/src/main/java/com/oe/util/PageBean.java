package com.oe.util;

import java.io.Serializable;
import java.util.List;

/**
 * @program: dongdongshop_parent
 * @description:
 * @author: zh
 * @create: 2020-12-10 18:54
 */
public class PageBean<T> implements Serializable {

    private int startIndex;//开始位置
    private int pageNumber;//当前页数
    private int pageSize;//每页条数
    private int totalPage;//总页数
    private int totalCount;//总条数
    private List<T> result;//结果集

    public int getStartIndex() {
        startIndex = (pageNumber - 1) * pageSize;
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getTotalPage() {
        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}