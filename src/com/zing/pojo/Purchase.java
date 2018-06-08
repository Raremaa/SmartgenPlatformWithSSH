package com.zing.pojo;

import com.zing.base.pojo.BasePojo;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单表
 * 与用户表多对一关系
 * 与产品表多对多关系 通过中间表product_purchase_relation表示关系 由订单表维护关系
 * 与商品评价信息表一对一关系 关系交给订单表维护
 */
public class Purchase extends BasePojo {

    private Integer purchaseNo;//订单编号
    private Timestamp purchasePaymentTime;//支付时间
    private String purchasePatternOfPayment;//支付方式
    private Byte purchaseState;//订单状态 0-待付款 1-待发货 2-待发货 3-待评价 4-取消订单
    private Integer purchaseCount;//购买数量
    private Byte purchaseClassify;//购买类型 0-预购 1-直接购买(从产品表中取保存下来)
    private String purchaseName;//产品名(从产品表中取出保存下来)
    private Double purchasePrice;//产品总价
    private String purchaseMsg;//买家留言
    private Double purchaseFreight;//运费(从产品表拉去)
    private Timestamp purchaseTime;//发货时间
    private String purchaseWay;//发货方式(具体哪家快递，空运之类)
    private User user;//外键关系-User_id-用户表-多对一
    private Set<Product> products = new HashSet<Product>();//外键关系-product_purchase_relation表-产品表-多对多
    private Productevalute productevalute;//外键关系-Productevalute_id-商品评价信息表-一对一

    public Integer getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(Integer purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public Timestamp getPurchasePaymentTime() {
        return purchasePaymentTime;
    }

    public void setPurchasePaymentTime(Timestamp purchasePaymentTime) {
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

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Byte getPurchaseClassify() {
        return purchaseClassify;
    }

    public void setPurchaseClassify(Byte purchaseClassify) {
        this.purchaseClassify = purchaseClassify;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseMsg() {
        return purchaseMsg;
    }

    public void setPurchaseMsg(String purchaseMsg) {
        this.purchaseMsg = purchaseMsg;
    }

    public Double getPurchaseFreight() {
        return purchaseFreight;
    }

    public void setPurchaseFreight(Double purchaseFreight) {
        this.purchaseFreight = purchaseFreight;
    }

    public Timestamp getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Timestamp purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getPurchaseWay() {
        return purchaseWay;
    }

    public void setPurchaseWay(String purchaseWay) {
        this.purchaseWay = purchaseWay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Productevalute getProductevalute() {
        return productevalute;
    }

    public void setProductevalute(Productevalute productevalute) {
        this.productevalute = productevalute;
    }
}
