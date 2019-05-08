package com.typ.travel.service;

import com.typ.travel.entity.User;

/**
 * @author typ
 * @date 2019/4/15 16:31
 * @Description: com.typ.travel.service
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
