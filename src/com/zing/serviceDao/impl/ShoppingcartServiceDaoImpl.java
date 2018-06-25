package com.zing.serviceDao.impl;

import com.zing.dao.ShoppingcartDao;
import com.zing.pojo.Shoppingcart;
import com.zing.queryparam.ShoppingcartQueryParam;
import com.zing.serviceDao.ShoppingcartServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("shoppingcartServiceDao")
public class ShoppingcartServiceDaoImpl implements ShoppingcartServiceDao {

    @Autowired
    private ShoppingcartDao shoppingcartDao;

    /**
     * 保存
     */
    @Override
    public Serializable save(Shoppingcart shoppingcart) throws Exception {
        return shoppingcartDao.save(shoppingcart);
    }

    /**
     * 修改
     */
    @Override
    public void update(Shoppingcart shoppingcart) throws Exception {
        shoppingcartDao.update(shoppingcart);
    }

    /**
     * 条件查询
     * 基于购物车表通用查询接口数据
     */
    @Override
    public List<Shoppingcart> getList(ShoppingcartQueryParam queryParam) throws Exception {
        return shoppingcartDao.getList(queryParam);
    }

    /**
     * 根据用户id查询购物车信息
     */
    @Override
    public List<Shoppingcart> getListByUserId(Integer id) throws Exception {
        return shoppingcartDao.getListByUserId(id);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Shoppingcart shoppingcart) throws Exception {
        shoppingcartDao.delete(shoppingcart);
    }
}
