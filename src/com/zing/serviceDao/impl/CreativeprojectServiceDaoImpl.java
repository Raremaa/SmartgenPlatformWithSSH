package com.zing.serviceDao.impl;

import com.zing.dao.CreativeprojectDao;
import com.zing.pojo.Creativeproject;
import com.zing.queryparam.CreQueryParam;
import com.zing.serviceDao.CreativeprojectServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("creativeprojectServiceDao")
public class CreativeprojectServiceDaoImpl implements CreativeprojectServiceDao {

    @Autowired
    private CreativeprojectDao creativeprojectDao;

    @Override
    /**
     * 条件查询
     */
    public List<Creativeproject> getCreativeprojectList(CreQueryParam creQueryParam) throws Exception {
        return creativeprojectDao.getCreativeprojectList(creQueryParam);
    }

    @Override
    /**
     * 修改创意项目表
     * 只更新非null字段
     */
    public Integer update(Creativeproject creativeproject) throws Exception {
        return creativeprojectDao.update(creativeproject);
    }

    @Override
    public Creativeproject getById(Integer id) throws Exception {
        return creativeprojectDao.getById(id);
    }
}
