package com.zing.serviceDao.impl;

import com.zing.dao.PurchaseItemDao;
import com.zing.pojo.Purchaseitem;
import com.zing.serviceDao.PurchaseitemServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("purchaseitemServiceDao")
public class PurchaseitemServiceDaoImpl implements PurchaseitemServiceDao {

    @Autowired
    private PurchaseItemDao purchaseItemDao;

    /**
     * 保存
     */
    @Override
    public Serializable save(Purchaseitem purchaseitem) throws Exception {
        return purchaseItemDao.save(purchaseitem);
    }

    /**
     * 修改
     * 成功返回1
     * 失败(为空)返回0
     */
    @Override
    public Integer update(Purchaseitem purchaseitem) throws Exception {
        return purchaseItemDao.update(purchaseitem);
    }

    /**
     * 根据订单表id查询订单详情
     */
    @Override
    public List<Purchaseitem> getPurchaseitemByPurchaseId(Integer id) throws Exception {
        return purchaseItemDao.getPurchaseitemByPurchaseId(id);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Purchaseitem purchaseitem) throws Exception {
        purchaseItemDao.delete(purchaseitem);
    }
}
