package com.zing.serviceDao.impl;

import com.zing.dao.CreativeremarkDao;
import com.zing.pojo.Creativeremark;
import com.zing.serviceDao.CreativeremarkServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("creativeremarkServiceDao")
public class CreativeremarkServiceDaoImpl implements CreativeremarkServiceDao {

    @Autowired
    private CreativeremarkDao creativeremarkDao;
    @Override
    /**
     * id查询
     */
    public Creativeremark getById(Integer id) throws Exception {
        return creativeremarkDao.getById(id);
    }

    /**
     * 根据用户表主键和创意项目表主键查询
     */
    @Override
    public Creativeremark getByUserAndCreProject(Integer UserId, Integer CreProjectId) throws Exception {
        return creativeremarkDao.getByUserAndCreProject(UserId,CreProjectId);
    }

    @Override
    /**
     * 修改
     * @return 1-正常修改 0-待修改对象不存在
     */
    public Integer update(Creativeremark creativeremark) throws Exception {
        return creativeremarkDao.update(creativeremark);
    }

    @Override
    /**
     * 添加数据
     * @return 返回该记录在表中的主键id
     */
    public Serializable save(Creativeremark creativeremark) throws Exception {
        return creativeremarkDao.save(creativeremark);
    }
}
