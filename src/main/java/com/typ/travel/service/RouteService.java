package com.typ.travel.service;

import com.typ.travel.entity.PageBean;
import com.typ.travel.entity.Route;

/**
 * @author typ
 * @date 2019/4/17 19:36
 * @Description: com.typ.travel.web.servlet
 */
public interface RouteService {
    /**
     * 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(String rid);
}
