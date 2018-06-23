package com.zing.dao;

import com.zing.pojo.Purchase;
import com.zing.queryparam.PurchaseQueryParam;

import java.io.Serializable;
import java.util.List;

public interface PurchaseDao {
    List<Purchase> getList (PurchaseQueryParam queryParam) throws Exception;
    Serializable save(Purchase purchase)throws Exception;
}
