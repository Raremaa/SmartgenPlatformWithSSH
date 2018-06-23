package com.zing.serviceDao;

import com.zing.pojo.Purchaseitem;

import java.io.Serializable;

public interface PurchaseitemServiceDao {
    Serializable save(Purchaseitem purchaseitem)throws Exception;
    Integer update(Purchaseitem purchaseitem)throws Exception;
}
