package com.typ.travel.entity;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/17 17:38
 * @Description: com.typ.travel.entity
 */
public class PageBean<T> {
    /**
     * 总记录数
     * 总页数
     * 当前页码
     * 每页显示的条数
     * 每页显示的数据集合
     */
    private Integer totalCount;
    private Integer totalPage;
    private Integer currentPage;
    private Integer pageSize;
    private List<T> list;

    public PageBean() {
    }

    public PageBean(Integer totalCount, Integer totalPage, Integer currentPage, Integer pageSize, List<T> list) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
