package com.zing.dao;

import com.zing.pojo.Product;
import com.zing.queryparam.ProductQueryParam;

import java.io.Serializable;
import java.util.List;

public interface ProductDao {
    List<Product> getProductList(ProductQueryParam queryParam) throws Exception;
    Serializable save(Product product)throws Exception;
    Product getById(Integer id)throws Exception;
}
