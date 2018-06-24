package com.zing.serviceDao;

import com.zing.pojo.Shoppingcart;
import com.zing.queryparam.ShoppingcartQueryParam;

import java.io.Serializable;
import java.util.List;

public interface ShoppingcartServiceDao {
    Serializable save(Shoppingcart shoppingcart)throws Exception;
    void update(Shoppingcart shoppingcart)throws Exception;
    List<Shoppingcart> getList(ShoppingcartQueryParam queryParam)throws Exception;
}
