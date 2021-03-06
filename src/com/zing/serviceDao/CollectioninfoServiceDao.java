package com.zing.serviceDao;

import com.zing.pojo.Collectioninfo;
import com.zing.pojo.Product;
import com.zing.queryparam.CollectioninfoQueryParam;

import java.util.List;

public interface CollectioninfoServiceDao {
    Integer save(Collectioninfo collectioninfo)throws Exception;
    List<Collectioninfo> getList(CollectioninfoQueryParam queryParam)throws Exception;
    Long getCount(CollectioninfoQueryParam queryParam)throws Exception;
    List<Product> getProductByUserId(Integer userId)throws Exception;
    void update(Collectioninfo collectioninfo)throws Exception;
    void delete(Collectioninfo collectioninfo)throws Exception;
}
