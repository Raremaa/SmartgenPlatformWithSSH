package com.zing.serviceDao;

import com.zing.pojo.Product;
import com.zing.queryparam.ProductQueryParam;
import java.util.List;

public interface ProductServiceDao {
    List<Product> getProductList(ProductQueryParam queryParam) throws Exception;
}
