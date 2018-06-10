package com.zing.dao;

import com.zing.pojo.User;

import java.io.Serializable;

public interface UserDao {
    public User getUserById(Serializable id) throws Exception;
}
