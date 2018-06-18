package com.zing.dao;

import com.zing.pojo.Product;
import com.zing.queryparam.ProductQueryParam;

import java.util.List;

public interface ProductDao {
    List<Product> getProductList(ProductQueryParam queryParam) throws Exception;
}
