package com.zing.pojo.view;

import com.zing.pojo.Purchaseitem;

import java.util.List;

/**
 * 订单与订单详情结果封装
 */
public class PurchaseView {
    List<Purchaseitem> purchaseitems;//订单详情
    private Integer purchaseId;//订单表主键
    private String purchaseNo;//订单编号
    private Double purchasePrice;//产品交易总价

    public List<Purchaseitem> getPurchaseitems() {
        return purchaseitems;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setPurchaseitems(List<Purchaseitem> purchaseitems) {
        this.purchaseitems = purchaseitems;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
