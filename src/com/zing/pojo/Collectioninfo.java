package com.zing.pojo;

/**
 * 收藏表
 * 与用户表多对一关系
 * 与产品表多对一关系
 */
public class Collectioninfo{

    private Integer id;//主键Id
    private Product product;//外键关系-Product_id-产品表-多对一
    private User user;//外键关系-User_id-用户表-多对一

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Collectioninfo{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                '}';
    }
}
