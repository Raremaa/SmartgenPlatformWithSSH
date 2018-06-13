package com.zing.dao;

import com.zing.pojo.User;
import java.io.Serializable;
import java.util.List;

public interface UserDao {

    User getUserById(Serializable id) throws Exception;
    List<User> getUserList(String condition) throws Exception;
    Integer save(User user) throws Exception;
    User login(String userPhone,String userPassword) throws Exception;
}
