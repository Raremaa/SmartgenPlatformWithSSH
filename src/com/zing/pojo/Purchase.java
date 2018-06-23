package com.zing.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 订单表
 * 与用户表多对一关系
 * 与订单详情表一对多关系
 * 与收货地址表多对一关系
 */
public class Purchase{

    private Integer id;//主键Id
    private String purchaseNo;//订单编号
    private Date purchasePaymentTime;//支付时间
    private String purchasePatternOfPayment;//支付方式
    private Byte purchaseState;//订单状态 0-待付款 1-已付款 2-取消订单
    private Double purchasePrice;//产品交易总价
    private Set<Purchaseitem> purchaseitems = new HashSet<Purchaseitem>();//外键关系-订单详情表-一对多
    private User user;//外键关系-User_id-用户表-多对一
    private Purchaseaddress purchaseaddress;//外键关系-Purchase_id-收货地址表-多对一

    //为了方便Struts2接受参数的冗余字段
    private Integer productId;//产品表主键
    private Integer purchaseitemCount;//商品购买数量
    private String purchaseitemMsg;//买家留言
    private List<Purchase> purchases;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPurchaseitemCount() {
        return purchaseitemCount;
    }

    public void setPurchaseitemCount(Integer purchaseitemCount) {
        this.purchaseitemCount = purchaseitemCount;
    }

    public String getPurchaseitemMsg() {
        return purchaseitemMsg;
    }

    public void setPurchaseitemMsg(String purchaseitemMsg) {
        this.purchaseitemMsg = purchaseitemMsg;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public Date getPurchasePaymentTime() {
        return purchasePaymentTime;
    }

    public void setPurchasePaymentTime(Date purchasePaymentTime) {
        this.purchasePaymentTime = purchasePaymentTime;
    }

    public String getPurchasePatternOfPayment() {
        return purchasePatternOfPayment;
    }

    public void setPurchasePatternOfPayment(String purchasePatternOfPayment) {
        this.purchasePatternOfPayment = purchasePatternOfPayment;
    }

    public Byte getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(Byte purchaseState) {
        this.purchaseState = purchaseState;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Set<Purchaseitem> getPurchaseitems() {
        return purchaseitems;
    }

    public void setPurchaseitems(Set<Purchaseitem> purchaseitems) {
        this.purchaseitems = purchaseitems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Purchaseaddress getPurchaseaddress() {
        return purchaseaddress;
    }

    public void setPurchaseaddress(Purchaseaddress purchaseaddress) {
        this.purchaseaddress = purchaseaddress;
    }

    public Purchase(Integer id, String purchaseNo, Date purchasePaymentTime, String purchasePatternOfPayment, Byte purchaseState, Double purchasePrice) {
        this.id = id;
        this.purchaseNo = purchaseNo;
        this.purchasePaymentTime = purchasePaymentTime;
        this.purchasePatternOfPayment = purchasePatternOfPayment;
        this.purchaseState = purchaseState;
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseNo='" + purchaseNo + '\'' +
                ", purchasePaymentTime=" + purchasePaymentTime +
                ", purchasePatternOfPayment='" + purchasePatternOfPayment + '\'' +
                ", purchaseState=" + purchaseState +
                ", purchasePrice=" + purchasePrice +
                ", purchaseitems=" + purchaseitems +
                ", user=" + user +
                '}';
    }

    public Purchase() {
    }
}
