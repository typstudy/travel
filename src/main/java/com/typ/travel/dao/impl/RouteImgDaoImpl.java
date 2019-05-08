package com.typ.travel.dao.impl;

import com.typ.travel.dao.RouteImgDao;
import com.typ.travel.entity.RouteImg;
import com.typ.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/18 15:13
 * @Description: com.typ.travel.dao.impl
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql="select * from tab_route_img where rid=?";
        return template.query(sql,
                new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
