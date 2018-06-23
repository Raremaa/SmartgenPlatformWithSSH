package com.zing.serviceDao.impl;

import com.zing.dao.PurchaseaddressDao;
import com.zing.pojo.Purchaseaddress;
import com.zing.queryparam.PurchaseaddressQueryParam;
import com.zing.serviceDao.PurchaseaddressServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("purchaseaddressServiceDao")
public class PurchaseaddressServiceDaoImpl implements PurchaseaddressServiceDao {

    @Autowired
    private PurchaseaddressDao purchaseaddressDao;

    /**
     * 条件查询
     * 基于通用查询数据接口
     */
    @Override
    public List<Purchaseaddress> getList(PurchaseaddressQueryParam queryParam) throws Exception {
        return purchaseaddressDao.getList(queryParam);
    }

    /**
     * 修改
     */
    @Override
    public Integer update(Purchaseaddress purchaseaddress) throws Exception {
        return purchaseaddressDao.update(purchaseaddress);
    }

    /**
     * 根据id查询
     */
    @Override
    public Purchaseaddress getById(Integer id) throws Exception {
        return purchaseaddressDao.getById(id);
    }

    /**
     * 保存
     */
    @Override
    public Serializable save(Purchaseaddress purchaseaddress) throws Exception {
        return purchaseaddressDao.save(purchaseaddress);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Purchaseaddress purchaseaddress) throws Exception {
        purchaseaddressDao.delete(purchaseaddress);
    }


}
