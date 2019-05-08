package com.typ.travel.dao;

import com.typ.travel.entity.Route;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/17 19:39
 * @Description: com.typ.travel.dao
 */
public interface RouteDao {
    /**
     * 根据cid查询总记录数
     * @param cid
     * @return
     */
    public int findTotalCount(int cid,String rname);

    /**
     * 根据cid,start,pageSize查询当前页面数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);

    /**
     * 根据cid查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);

}
