package com.zing.serviceDao;

import com.zing.pojo.Collectioninfo;
import com.zing.queryparam.CollectioninfoQueryParam;

import java.util.List;

public interface CollectioninfoServiceDao {
    Integer save(Collectioninfo collectioninfo)throws Exception;
    List<Collectioninfo> getList(CollectioninfoQueryParam queryParam)throws Exception;
}
