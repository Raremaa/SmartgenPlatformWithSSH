package com.zing.serviceDao;

import com.zing.pojo.User;

import java.io.Serializable;

public interface UserServiceDao {
    User getUserById(Serializable id) throws Exception;
}
