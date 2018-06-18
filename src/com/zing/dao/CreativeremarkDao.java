package com.zing.dao;

import com.zing.pojo.Creativeremark;

import java.io.Serializable;

public interface CreativeremarkDao {
    Creativeremark getById(Integer id) throws Exception;
    Creativeremark getByUserAndCreProject(Integer UserId,Integer CreProjectId) throws Exception;
    Integer update(Creativeremark  creativeremark)throws Exception;
    Serializable save(Creativeremark creativeremark)throws Exception;
}
