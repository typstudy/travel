package com.typ.travel.service;

/**
 * @author typ
 * @date 2019/4/18 17:01
 * @Description: com.typ.travel.service
 */
public interface FavoriteService {
    /**
     * 判断是否收藏
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(String rid,int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
