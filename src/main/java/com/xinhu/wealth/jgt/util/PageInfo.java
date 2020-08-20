package com.xinhu.wealth.jgt.util;

import java.io.Serializable;
import java.util.Collection;


public class PageInfo<T> implements Serializable{
    private long total;
    private int pageNum;
    private int pageSize;
    private Collection<T> content;

    public PageInfo() {
    }

    public PageInfo(int pageNum, int pageSize) {
        if (pageNum<0 || pageSize<=0)throw new RuntimeException("分页数据无效！");
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum<0)throw new RuntimeException("分页数据无效！");
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize<=0)throw new RuntimeException("分页数据无效！");
        this.pageSize = pageSize;
    }

    public Collection<T> getContent() {
        return content;
    }

    public void setContent(Collection<T> content) {
        this.content = content;
    }

    public int getOffSet(){
        return pageNum*pageSize;
    }

    public PageInfo(long total, int pageNum, int pageSize, Collection<T> content) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.content = content;
    }
}
