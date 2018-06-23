package com.zing.serviceDao;

import com.zing.pojo.Purchaseaddress;
import com.zing.queryparam.PurchaseaddressQueryParam;

import java.io.Serializable;
import java.util.List;

public interface PurchaseaddressServiceDao {
    List<Purchaseaddress> getList(PurchaseaddressQueryParam queryParam)throws Exception;
    Integer update(Purchaseaddress purchaseaddress)throws Exception;
    Purchaseaddress getById(Integer id)throws Exception;
    Serializable save(Purchaseaddress purchaseaddress)throws Exception;
    void delete(Purchaseaddress purchaseaddress)throws Exception;
}
