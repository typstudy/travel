package com.typ.travel.dao;

import com.typ.travel.entity.User;

/**
 * @author typ
 * @date 2019/4/15 16:31
 * @Description: com.typ.travel.dao
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    /**
     *根据激活码查询用户信息
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     *修改激活状态
     * @param user
     */
    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
