package com.zing.dao;

import com.zing.pojo.Purchaseitem;

import java.io.Serializable;
import java.util.List;

public interface PurchaseItemDao {
    Serializable save(Purchaseitem purchaseitem)throws Exception;
    Integer update(Purchaseitem purchaseitem)throws Exception;
    List<Purchaseitem> getPurchaseitemByPurchaseId(Integer id)throws Exception;
    void delete(Purchaseitem purchaseitem)throws Exception;
}
