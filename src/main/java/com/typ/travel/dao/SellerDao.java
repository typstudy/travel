package com.typ.travel.dao;

import com.typ.travel.entity.Seller;

/**
 * @author typ
 * @date 2019/4/18 15:22
 * @Description: com.typ.travel.dao
 */
public interface SellerDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Seller findById(int id);
}
