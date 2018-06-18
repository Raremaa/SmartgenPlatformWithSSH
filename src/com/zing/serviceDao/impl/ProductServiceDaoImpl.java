package com.zing.serviceDao.impl;

import com.zing.dao.ProductDao;
import com.zing.pojo.Product;
import com.zing.queryparam.ProductQueryParam;
import com.zing.serviceDao.ProductServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServiceDao")
public class ProductServiceDaoImpl implements ProductServiceDao {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> getProductList(ProductQueryParam queryParam) throws Exception {
        return productDao.getProductList(queryParam);
    }
}
