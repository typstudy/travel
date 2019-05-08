package com.typ.travel.dao.impl;

import com.typ.travel.dao.SellerDao;
import com.typ.travel.entity.Seller;
import com.typ.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author typ
 * @date 2019/4/18 15:23
 * @Description: com.typ.travel.dao.impl
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findById(int id) {
        String sql="select * from tab_seller where sid=?";
        return template.queryForObject(sql,
                new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
