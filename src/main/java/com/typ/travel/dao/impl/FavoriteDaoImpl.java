package com.typ.travel.dao.impl;

import com.typ.travel.dao.FavoriteDao;
import com.typ.travel.entity.Favorite;
import com.typ.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @author typ
 * @date 2019/4/18 17:05
 * @Description: com.typ.travel.dao.impl
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid=? and uid=?";
            favorite = template.queryForObject(sql,
                    new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            return null;
        }
        return favorite;
    }

    @Override
    public int findByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid=?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";

        template.update(sql,rid,new Date(),uid);
    }
}
