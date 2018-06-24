package com.zing.serviceDao.impl;

import com.zing.dao.CollectioninfoDao;
import com.zing.pojo.Collectioninfo;
import com.zing.pojo.Product;
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
    /**
     *保存
     */
    public Integer save(Collectioninfo collectioninfo) throws Exception {
        return collectioninfoDao.save(collectioninfo);
    }

    @Override
    /**
     * 条件查询
     */
    public List<Collectioninfo> getList(CollectioninfoQueryParam queryParam) throws Exception {
        return collectioninfoDao.getList(queryParam);
    }

    @Override
    /**
     * 查找符合条件的数据条数
     */
    public Long getCount(CollectioninfoQueryParam queryParam) throws Exception {
        return collectioninfoDao.getCount(queryParam);
    }

    @Override
    /**
     * 根据用户id获取用户收藏的产品信息
     */
    public List<Product> getProductByUserId(Integer userId) throws Exception {
        return collectioninfoDao.getProductByUserId(userId);
    }

    /**
     * 修改信息
     */
    @Override
    public void update(Collectioninfo collectioninfo) throws Exception {
       collectioninfoDao.update(collectioninfo);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Collectioninfo collectioninfo) throws Exception {
        collectioninfoDao.delete(collectioninfo);
    }
}
