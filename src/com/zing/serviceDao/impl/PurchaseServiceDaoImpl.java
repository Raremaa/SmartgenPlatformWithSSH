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
}
