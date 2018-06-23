package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Product;
import com.zing.pojo.Purchase;
import com.zing.pojo.Purchaseitem;
import com.zing.serviceDao.ProductServiceDao;
import com.zing.serviceDao.PurchaseServiceDao;
import com.zing.serviceDao.PurchaseitemServiceDao;
import com.zing.util.JsonResultForMapUtil;
import com.zing.util.PurchaseNoGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("purchaseAction")
@Scope("prototype")
public class PurchaseAction extends ActionSupport implements ModelDriven<Purchase> {

    private Purchase purchase = new Purchase();
    private Map<String,Object> datas = new HashMap<String,Object>(0);
    @Autowired
    private PurchaseServiceDao purchaseServiceDao;
    @Autowired
    private PurchaseitemServiceDao purchaseitemServiceDao;
    @Autowired
    private ProductServiceDao productServiceDao;
    /**
     * 购买业务
     * 这里算法较为复杂 后续可大幅度优化
     */
    public String purchse(){
         JsonResult jsonResult = new JsonResult();
        Purchase purchaseTemp = new Purchase();
        Set<Purchaseitem> purchaseitems = new HashSet<Purchaseitem>(0);
        try {
            //判断订单详情信息是否传到（productId;//产品表主键 Integer purchaseitemCount;//商品购买数量 purchaseitemMsg;//买家留言）
            if(purchase.getPurchases() == null){
                jsonResult.setMsg("您还未传入参数！");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            //判断用户信息是否传到
            if(purchase.getUser() == null || purchase.getUser().getId() == null){
                jsonResult.setMsg("您还未传入用户信息");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }

            Double sumPrice = 0.0;//交易总价计算中间值
            //设置订单详情拼装为Set对象便于后续封装入订单中
            for(Purchase p:purchase.getPurchases()){
                Purchaseitem purchaseitem = new Purchaseitem();
                purchaseitem.setPurchaseitemMsg(p.getPurchaseitemMsg());
                Product pTemp = productServiceDao.getById(p.getProductId());
                if (pTemp == null){
                    jsonResult.setMsg("查无此产品");
                    JsonResultForMapUtil.packageClass(datas,jsonResult);
                    return SUCCESS;
                }
                purchaseitem.setProduct(pTemp);
                purchaseitem.setPurchaseitemCount(p.getPurchaseitemCount());
                purchaseitems.add(purchaseitem);
                purchaseitem.setPurchaseitemStatus((byte) 0);//设置初始发货状态为0-未发货
                purchaseitem.setPurchaseitemSinglePrice(purchaseitem.getProduct().getProductPrice());
                purchaseitem.setPurchaseitemPrice(purchaseitem.getPurchaseitemSinglePrice()*purchaseitem.getPurchaseitemCount());
                purchaseitem.setPurchaseitemName(purchaseitem.getProduct().getProductName());
                sumPrice += purchaseitem.getPurchaseitemPrice();
            }
            //将Set对象存入订单表中 同时设置订单表相关初始参数
            purchaseTemp.setPurchaseitems(purchaseitems);
            purchaseTemp.setPurchaseNo(PurchaseNoGenerate.doGenerate(new Date(),purchase.getUser().getId()));
            purchaseTemp.setUser(purchase.getUser());
            purchaseTemp.setPurchaseState((byte) 0);//设置初始付款状态为0-待付款
            purchaseTemp.setPurchasePrice(sumPrice);
            purchaseTemp.setPurchaseaddress(purchase.getPurchaseaddress());
            purchaseServiceDao.save(purchaseTemp);
            jsonResult.setMsg("下单成功！");
            List<Purchase> result = new ArrayList<Purchase>(0);
            result.add(purchaseTemp);
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    @Override
    public Purchase getModel() {
        return this.purchase;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }
}
