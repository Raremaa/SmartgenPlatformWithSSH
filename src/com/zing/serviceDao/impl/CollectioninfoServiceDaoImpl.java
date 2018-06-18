package com.zing.serviceDao.impl;

import com.zing.dao.CollectioninfoDao;
import com.zing.pojo.Collectioninfo;
import com.zing.queryparam.CollectioninfoQueryParam;
import com.zing.serviceDao.CollectioninfoServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collectioninfoServiceDao")
public class CollectioninfoServiceDaoImpl implements CollectioninfoServiceDao {

    @Autowired
    private CollectioninfoDao collectioninfoDao;
    @Override
    public Integer save(Collectioninfo collectioninfo) throws Exception {
        return collectioninfoDao.save(collectioninfo);
    }

    @Override
    public List<Collectioninfo> getList(CollectioninfoQueryParam queryParam) throws Exception {
        return collectioninfoDao.getList(queryParam);
    }
}
