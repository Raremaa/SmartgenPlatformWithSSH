package com.zing.serviceDao.impl;

import com.zing.dao.UserDao;
import com.zing.pojo.User;
import com.zing.serviceDao.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("userServiceDao")
public class UserServiceDaoImpl implements UserServiceDao {

    @Autowired
    private UserDao userDao;

    @Override
    /**
     * 根据id查询用户信息
     */
    public User getUserById(Serializable id) throws Exception {
        return userDao.getUserById(id);
    }

    @Override
    /**
     * 条件查询用户信息
     */
    public List<User> getUserList(String condition) throws Exception {
        return userDao.getUserList(condition);
    }

    @Override
    public Integer save(User user) throws Exception {
        userDao.save(user);
        return 1;
    }
}
