package com.typ.travel.service;

import com.typ.travel.entity.Category;

import java.util.List;

/**
 * @author typ
 * @date 2019/4/17 15:58
 * @Description: com.typ.travel.service
 */
public interface CategoryService {
    /**
     * 查询所有
     * @return
     */
    public List<Category> findAll();
}
