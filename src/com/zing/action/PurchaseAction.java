package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Product;
import com.zing.pojo.Purchase;
import com.zing.pojo.Purchaseitem;
import com.zing.pojo.view.PurchaseView;
import com.zing.queryparam.PurchaseQueryParam;
import com.zing.serviceDao.ProductServiceDao;
import com.zing.serviceDao.PurchaseServiceDao;
import com.zing.serviceDao.PurchaseitemServiceDao;
import com.zing.util.DateUtil;
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
    private PurchaseQueryParam queryParam = new PurchaseQueryParam();
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
                purchaseitem.setPurchaseitemStatus((byte) 0);//设置初始发货状态为0-未发货
                purchaseitem.setPurchaseitemPicture(purchaseitem.getProduct().getProductPicture());
                purchaseitem.setPurchaseitemSinglePrice(purchaseitem.getProduct().getProductPrice());
                purchaseitem.setPurchaseitemPrice(purchaseitem.getPurchaseitemSinglePrice()*purchaseitem.getPurchaseitemCount());
                purchaseitem.setPurchaseitemName(purchaseitem.getProduct().getProductName());
                purchaseitems.add(purchaseitem);
                sumPrice += purchaseitem.getPurchaseitemPrice();
            }
            //将Set对象存入订单表中 同时设置订单表相关初始参数
            purchaseTemp.setPurchaseitems(purchaseitems);
            purchaseTemp.setPurchaseNo(PurchaseNoGenerate.doGenerate(new Date(),purchase.getUser().getId()));
            purchaseTemp.setUser(purchase.getUser());
            purchaseTemp.setPurchaseState((byte) 0);//设置初始付款状态为0-待付款
            purchaseTemp.setPurchasePrice(sumPrice);
            purchaseTemp.setPurchaseaddress(purchase.getPurchaseaddress());
//            purchaseServiceDao.save(purchaseTemp);
            //这里由于多对一由多的一方维护关系 所以必须调用多的一方的保存方法 不然没法进行外键维护
            for(Purchaseitem p:purchaseitems){
                p.setPurchase(purchaseTemp);
                purchaseitemServiceDao.save(p);
            }
            jsonResult.setMsg("下单成功！");
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /**
     * 获取订单详情信息
     */
    public String getPurchase(){
        JsonResult jsonResult = new JsonResult();
        try {
            if(queryParam.getCondition() == null){
                jsonResult.setMsg("您还未传入相关信息");
            }else{
                List<Purchase> plist = purchaseServiceDao.getList(queryParam);
                if (plist == null){
                    jsonResult.setMsg("订单信息不存在！");
                    JsonResultForMapUtil.packageClass(datas,jsonResult);
                    return SUCCESS;
                }
                List<PurchaseView> result = new ArrayList<PurchaseView>(0);
                for(Purchase p : plist){
                    PurchaseView pView = new PurchaseView();
                    pView.setPurchaseNo(p.getPurchaseNo());
                    pView.setPurchasePrice(p.getPurchasePrice());
                    List<Purchaseitem> pItemTemp = purchaseitemServiceDao.getPurchaseitemByPurchaseId(p.getId());
                    pView.setPurchaseitems(pItemTemp);
                    pView.setPurchaseId(p.getId());
                    result.add(pView);
                }
                jsonResult.setMsg("查询成功");
                jsonResult.setSuccess(true);
                jsonResult.setTotal(result.size());
                jsonResult.setDatas(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /**
     * 删除订单和订单信息
     */
    public String delete(){
        JsonResult jsonResult = new JsonResult();
        //判断前台id是否接收到
        if (purchase.getId() == null ){
            jsonResult.setMsg("您还未传入订单信息");
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
        //判断待删除对象是否存在
        PurchaseQueryParam qTemp = new PurchaseQueryParam();
        qTemp.setCondition("id ="+purchase.getId());
        try {
            List<Purchase> purchasesTemp = purchaseServiceDao.getList(qTemp);
            if(purchasesTemp == null || purchasesTemp.size() == 0){
                jsonResult.setMsg("订单信息不存在");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            List<Purchaseitem> purchaseitemsList = purchaseitemServiceDao.getPurchaseitemByPurchaseId(purchase.getId());
            for(Purchaseitem p:purchaseitemsList){
                purchaseitemServiceDao.delete(p);
            }
            purchaseServiceDao.delete(purchasesTemp.get(0));
            jsonResult.setMsg("删除成功");
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /*public String getList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Purchase> purchaseList = purchaseServiceDao.getList(queryParam);
            jsonResult.setDatas(purchaseList);
            jsonResult.setMsg("查询成功");
            jsonResult.setSuccess(true);
            jsonResult.setTotal(purchaseList.size());
            jsonResult.setPage(queryParam.getPage());
            jsonResult.setPageSize(queryParam.getPageSize());
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }*/

    /**
     * 修改订单支付信息
     * 订单状态:0为待付款 1为已付款 2为取消订单
     */
    public String updatePayInformation(){
        JsonResult jsonResult = new JsonResult();
        try {
            if(purchase.getId() == null){
                jsonResult.setMsg("请传入订单信息！");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            List<Purchase> purchaseList = purchaseServiceDao.getById(purchase.getId());
            if(purchaseList == null || purchaseList.size() == 0){
                jsonResult.setMsg("待修改订单不存在！请重试");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            Purchase pTemp = purchaseList.get(0);
            //修改支付信息
            if(purchase.getPurchasePatternOfPayment() != null){
                pTemp.setPurchasePatternOfPayment(purchase.getPurchasePatternOfPayment());
                pTemp.setPurchasePaymentTime(new Date());
                pTemp.setPurchaseState((byte) 1);//订单状态:0为待付款 1为已付款 2为取消订单
                jsonResult.setMsg("订单支付成功！您的商品将尽快安排发货！");
                jsonResult.setSuccess(true);
            }
            purchaseServiceDao.update(pTemp);
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /**
     * 取消订单
     * 订单状态:0为待付款 1为已付款 2为取消订单
     * 只可取消代付款状态订单
     */
    public String cancel(){
        JsonResult jsonResult = new JsonResult();
        try {
            if(purchase.getId() == null){
                jsonResult.setMsg("请传入订单信息！");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            List<Purchase> purchaseList = purchaseServiceDao.getById(purchase.getId());
            if(purchaseList == null || purchaseList.size() == 0){
                jsonResult.setMsg("待修改订单不存在！请重试");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            Purchase pTemp = purchaseList.get(0);
            //取消订单(修改订单状态) 订单状态:0为待付款 1为已付款 2为取消订单
            if(pTemp.getPurchaseState() == 1){
                jsonResult.setMsg("抱歉！您的订单已经付款 无法取消！您可尝试退换货处理！");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            if(pTemp.getPurchaseState() == 2){
                jsonResult.setMsg("抱歉！您的订单已经取消,无需重复取消！");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            if(pTemp.getPurchaseState() == 0){
                pTemp.setPurchaseState((byte) 2);//订单状态:0为待付款 1为已付款 2为取消订单
                purchaseServiceDao.update(pTemp);
                jsonResult.setMsg("您的订单取消成功！");
                jsonResult.setSuccess(true);
            }
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    public PurchaseQueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(PurchaseQueryParam queryParam) {
        this.queryParam = queryParam;
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
