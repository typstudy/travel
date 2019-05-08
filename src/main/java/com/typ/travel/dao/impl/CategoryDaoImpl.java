package com.typ.travel.dao.impl;

import com.typ.travel.dao.CategoryDao;
import com.typ.travel.entity.Category;
import com.typ.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/17 15:53
 * @Description: com.typ.travel.dao.impl
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        return template.query(sql,
                new BeanPropertyRowMapper<Category>(Category.class));
    }
}
