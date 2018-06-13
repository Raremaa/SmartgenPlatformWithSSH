package com.zing.dao;

import com.zing.pojo.Creativeproject;
import com.zing.pojo.User;
import com.zing.queryParam.CreativeprojectQueryParam;

import java.util.List;

public interface CreativeprojectDao {
    List<Creativeproject> getCreativeprojectList(CreativeprojectQueryParam creativeprojectQueryParam) throws Exception;
}
