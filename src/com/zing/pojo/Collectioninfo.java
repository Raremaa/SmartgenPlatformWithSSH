package com.zing.pojo;

import com.zing.base.pojo.BasePojo;

/**
 * 收藏表
 * 与用户表多对一关系
 * 与产品表多对一关系
 */
public class Collectioninfo extends BasePojo {

    private Product product;//外键关系-Product_id-产品表-多对一
    private User user;//外键关系-User_id-用户表-多对一

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
