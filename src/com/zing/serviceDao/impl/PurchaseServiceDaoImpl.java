package com.zing.serviceDao.impl;

import com.zing.dao.PurchaseDao;
import com.zing.pojo.Purchase;
import com.zing.queryparam.PurchaseQueryParam;
import com.zing.serviceDao.PurchaseServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("purchaseServiceDao")
public class PurchaseServiceDaoImpl implements PurchaseServiceDao {

    @Autowired
    private PurchaseDao purchaseDao;

    /**
     * id查询
     */
    @Override
    public List<Purchase> getById(Integer id) throws Exception {
        return purchaseDao.getById(id);
    }

    /**
     * 条件查询
     * 基于订单表通用查询接口数据
     */
    @Override
    public List<Purchase> getList(PurchaseQueryParam queryParam) throws Exception {
        return purchaseDao.getList(queryParam);
    }

    /**
     * 保存
     */
    @Override
    public Serializable save(Purchase purchase) throws Exception {
        return purchaseDao.save(purchase);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Purchase purchase) throws Exception {
        purchaseDao.delete(purchase);
    }

    /**
     * 修改
     */
    @Override
    public void update(Purchase purchase) throws Exception {
        purchaseDao.update(purchase);
    }
}
