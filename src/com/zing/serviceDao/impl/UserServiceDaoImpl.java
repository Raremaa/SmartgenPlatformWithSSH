package com.zing.serviceDao.impl;

import com.zing.dao.UserDao;
import com.zing.pojo.User;
import com.zing.serviceDao.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("userServiceDao")
public class UserServiceDaoImpl implements UserServiceDao {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Serializable id) throws Exception {
        return userDao.getUserById(id);
    }
}
