package com.zing.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 收货地址表
 * 与用户表多对一关系
 * 与订单表一对多关系
 */
public class Purchaseaddress{

    private Integer id;//主键Id
    private Byte puraddressIsChoice;//是否为默认地址 1-是 2-不是
    private String puraddressUserName;//收货人姓名
    private String puraddressAddress;//收货地址
    private String puraddressUserPhone;//收货人手机号码
    private String puraddressZipcode;//收货人邮编
    private String puraddressProvince;//收货人所在省份
    private String puraddressCity;//收货人所在城市
    private User user;//外键关联-User_id-用户表-多对一
    private Set<Purchase> purchases = new HashSet<Purchase>();//外键关联-订单表-一对多

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Byte getPuraddressIsChoice() {
        return puraddressIsChoice;
    }

    public void setPuraddressIsChoice(Byte puraddressIsChoice) {
        this.puraddressIsChoice = puraddressIsChoice;
    }

    public String getPuraddressUserName() {
        return puraddressUserName;
    }

    public void setPuraddressUserName(String puraddressUserName) {
        this.puraddressUserName = puraddressUserName;
    }

    public String getPuraddressAddress() {
        return puraddressAddress;
    }

    public void setPuraddressAddress(String puraddressAddress) {
        this.puraddressAddress = puraddressAddress;
    }

    public String getPuraddressUserPhone() {
        return puraddressUserPhone;
    }

    public void setPuraddressUserPhone(String puraddressUserPhone) {
        this.puraddressUserPhone = puraddressUserPhone;
    }

    public String getPuraddressZipcode() {
        return puraddressZipcode;
    }

    public void setPuraddressZipcode(String puraddressZipcode) {
        this.puraddressZipcode = puraddressZipcode;
    }

    public String getPuraddressProvince() {
        return puraddressProvince;
    }

    public void setPuraddressProvince(String puraddressProvince) {
        this.puraddressProvince = puraddressProvince;
    }

    public String getPuraddressCity() {
        return puraddressCity;
    }

    public void setPuraddressCity(String puraddressCity) {
        this.puraddressCity = puraddressCity;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Purchaseaddress() {
    }

    public Purchaseaddress(Integer id, Byte puraddressIsChoice, String puraddressUserName, String puraddressAddress, String puraddressUserPhone, String puraddressZipcode, String puraddressProvince, String puraddressCity) {
        this.id = id;
        this.puraddressIsChoice = puraddressIsChoice;
        this.puraddressUserName = puraddressUserName;
        this.puraddressAddress = puraddressAddress;
        this.puraddressUserPhone = puraddressUserPhone;
        this.puraddressZipcode = puraddressZipcode;
        this.puraddressProvince = puraddressProvince;
        this.puraddressCity = puraddressCity;
    }

    @Override
    public String toString() {
        return "Purchaseaddress{" +
                "id=" + id +
                ", puraddressIsChoice=" + puraddressIsChoice +
                ", puraddressUserName='" + puraddressUserName + '\'' +
                ", puraddressAddress='" + puraddressAddress + '\'' +
                ", puraddressUserPhone='" + puraddressUserPhone + '\'' +
                ", puraddressZipcode='" + puraddressZipcode + '\'' +
                ", puraddressProvince='" + puraddressProvince + '\'' +
                ", puraddressCity='" + puraddressCity + '\'' +
                '}';
    }
}
