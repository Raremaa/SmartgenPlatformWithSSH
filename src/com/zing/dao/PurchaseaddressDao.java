package com.zing.dao;

import com.zing.pojo.Purchaseaddress;
import com.zing.queryparam.PurchaseaddressQueryParam;

import java.io.Serializable;
import java.util.List;

public interface PurchaseaddressDao {
    List<Purchaseaddress> getList(PurchaseaddressQueryParam queryParam)throws Exception;
    Serializable save(Purchaseaddress purchaseaddress)throws Exception;
    Integer update(Purchaseaddress purchaseaddress)throws Exception;
    Purchaseaddress getById(Integer id)throws Exception;
    void delete(Purchaseaddress purchaseaddress)throws Exception;
}
