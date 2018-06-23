package com.zing.dao;

import com.zing.pojo.Purchaseitem;

import java.io.Serializable;

public interface PurchaseItemDao {
    Serializable save(Purchaseitem purchaseitem)throws Exception;
    Integer update(Purchaseitem purchaseitem)throws Exception;
}
