package com.typ.travel.dao;

import com.typ.travel.entity.Favorite;

/**
 * @author typ
 * @date 2019/4/18 17:04
 * @Description: com.typ.travel.dao
 */
public interface FavoriteDao {
    /**
     * 根据rid和uid 查询收藏信息
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid,int uid);

    /**
     * 根据rid查询收藏次数
     * @param rid
     * @return
     */
    public int findByRid(int rid);

    /**
     *  添加收藏
     * @param rid
     * @param uid
     */
    public void add(int rid, int uid);
}
