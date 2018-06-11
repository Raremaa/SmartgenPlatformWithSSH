package com.zing.serviceDao;

import com.zing.pojo.User;

import java.io.Serializable;
import java.util.List;

public interface UserServiceDao {
    User getUserById(Serializable id) throws Exception;
    List<User> getUserList(String condition) throws Exception;
    Integer save(User user) throws Exception;
}
