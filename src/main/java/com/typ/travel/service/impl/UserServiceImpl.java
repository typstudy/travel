package com.typ.travel.service.impl;

import com.typ.travel.dao.UserDao;
import com.typ.travel.dao.impl.UserDaoImpl;
import com.typ.travel.entity.ResultInfo;
import com.typ.travel.entity.User;
import com.typ.travel.service.UserService;
import com.typ.travel.util.MailUtils;
import com.typ.travel.util.UuidUtil;

/**
 * @author typ
 * @date 2019/4/15 16:31
 * @Description: com.typ.travel.service.impl
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //根据用户名查询用户信息
        User u = userDao.findByUsername(user.getUsername());
        ResultInfo info=new ResultInfo();
        //判断u是否为空
        if(u!=null){
            //用户名已经存在，注册失败
            return false;
        }
        //保存用户信息
        //设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        userDao.save(user);
        //发送激活邮件
        String content="<a href='http://localhost:8082/travel" +
                "/user/active?code="+user.getCode()+"'>点击激活</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据激活码查询用户
        User user=userDao.findByCode(code);
        if(code!=null){
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
