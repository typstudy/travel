package com.typ.travel.service.impl;

import com.typ.travel.dao.FavoriteDao;
import com.typ.travel.dao.impl.FavoriteDaoImpl;
import com.typ.travel.entity.Favorite;
import com.typ.travel.service.FavoriteService;

/**
 * @author typ
 * @date 2019/4/18 17:03
 * @Description: com.typ.travel.service.impl
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
        //如果对象有值，则为true，反之，则为false
        return favorite != null;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
