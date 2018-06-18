package com.zing.dao;

import com.zing.pojo.Creativeproject;
import com.zing.pojo.User;
import com.zing.queryparam.CreQueryParam;
import java.util.List;

public interface CreativeprojectDao {
    List<Creativeproject> getCreativeprojectList(CreQueryParam creQueryParam) throws Exception;
    Integer update(Creativeproject creativeproject) throws Exception;
    Creativeproject getById(Integer id)throws Exception;
}
