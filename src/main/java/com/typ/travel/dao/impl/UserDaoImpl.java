package com.typ.travel.dao.impl;

import com.typ.travel.dao.UserDao;
import com.typ.travel.entity.User;
import com.typ.travel.util.JDBCUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author typ
 * @date 2019/4/15 16:32
 * @Description: com.typ.travel.dao.impl
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template;

    public UserDaoImpl() {
        template = new JdbcTemplate(JDBCUtils.getDataSource());
    }

    @Override
    public User findByUsername(String username) {
        //定义sql
        User user = null;

        try {
            String sql = "select * from tab_user where username=?";
            //执行SQL
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday," +
                "sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    /**
     * 根据激活码查询用户信息
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        String sql="select * from tab_user where code=?";
        User user=null;
        try {
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class), code);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }

    /**
     * 修改激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql="update tab_user set status='Y' where uid=?";
        template.update(sql,user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;

        try {
            String sql = "select * from tab_user where username=? and " +
                    "password=?";
            //执行SQL
            user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),username,password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return user;
    }
}
