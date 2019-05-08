package com.typ.travel.dao;

import com.typ.travel.entity.Category;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/17 15:51
 * @Description: com.typ.travel.dao
 */
public interface CategoryDao {
    /**
     * 查询所有类别
     * @return
     */
    public List<Category> findAll();
}
