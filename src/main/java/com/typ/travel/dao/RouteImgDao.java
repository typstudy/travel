package com.typ.travel.dao;

import com.typ.travel.entity.RouteImg;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/18 15:11
 * @Description: com.typ.travel.dao
 */
public interface RouteImgDao {
    /**
     * 通过route的id查询图片集合
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
