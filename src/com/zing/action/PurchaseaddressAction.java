package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Purchaseaddress;
import com.zing.queryparam.PurchaseaddressQueryParam;
import com.zing.serviceDao.PurchaseaddressServiceDao;
import com.zing.util.JsonResultForMapUtil;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("purchaseaddressAction")
@Scope("prototype")
public class PurchaseaddressAction extends ActionSupport implements ModelDriven<Purchaseaddress> {

    private Map<String,Object> datas = new HashMap<String,Object>(0);
    private Purchaseaddress purchaseaddress = new Purchaseaddress();
    private PurchaseaddressQueryParam queryParam = new PurchaseaddressQueryParam();
    @Autowired
    private PurchaseaddressServiceDao purchaseaddressServiceDao;

    /**
     * 条件查询
     * 基于地址表通用查询接口数据
     */
    @JSON(serialize = false)
    public String getList (){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Purchaseaddress> list = new ArrayList<Purchaseaddress>(0);
            list = purchaseaddressServiceDao.getList(this.queryParam);
            jsonResult.setDatas(list);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("查询成功");
            jsonResult.setTotal(list.size());
            jsonResult.setPageSize(queryParam.getPageSize());
            jsonResult.setPage(queryParam.getPage());
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /**
     * 修改
     * 修改非空字段
     */
    public String update(){
        JsonResult jsonResult = new JsonResult();
        try {
            //修改之前先判断是否存在
            Purchaseaddress p = purchaseaddressServiceDao.getById(purchaseaddress.getId());
            if(p == null){
                jsonResult.setMsg("待修改的地址不存在");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            if(purchaseaddress.getPuraddressIsChoice() != null){
                p.setPuraddressIsChoice(purchaseaddress.getPuraddressIsChoice());
            }
            if(purchaseaddress.getPuraddressUserName() != null){
                p.setPuraddressUserName(purchaseaddress.getPuraddressUserName());
            }
            if(purchaseaddress.getPuraddressAddress() != null){
                p.setPuraddressAddress(purchaseaddress.getPuraddressAddress());
            }
            if(purchaseaddress.getPuraddressUserPhone() != null){
                p.setPuraddressUserPhone(purchaseaddress.getPuraddressUserPhone());
            }
            if(purchaseaddress.getPuraddressZipcode() != null){
                p.setPuraddressZipcode(purchaseaddress.getPuraddressZipcode());
            }
            if(purchaseaddress.getPuraddressProvince() != null){
                p.setPuraddressProvince(purchaseaddress.getPuraddressProvince());
            }
            if(purchaseaddress.getPuraddressCity() != null){
                p.setPuraddressCity(purchaseaddress.getPuraddressCity());
            }
            purchaseaddressServiceDao.update(p);
            jsonResult.setMsg("修改成功");
            jsonResult.setTotal(1);
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
     * 保存
     */
    public String save(){
        JsonResult jsonResult = new JsonResult();
        try {
            if(purchaseaddress == null){
                jsonResult.setMsg("请传入地址！");
            }else {
                purchaseaddressServiceDao.save(purchaseaddress);
                jsonResult.setSuccess(true);
                jsonResult.setMsg("插入成功！");
            }
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }
    /**
     * 删除
     */
    public String delete(){
       JsonResult jsonResult = new JsonResult();
        try {
            //先判断待删除对象是否存在
            PurchaseaddressQueryParam q = new PurchaseaddressQueryParam();
            q.setCondition("id="+purchaseaddress.getId());
            List<Purchaseaddress> l = new ArrayList<Purchaseaddress>(0);
            l = purchaseaddressServiceDao.getList(q);
            if(l == null){
                jsonResult.setMsg("待删除地址不存在！");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            //存在则删除
            purchaseaddressServiceDao.delete(l.get(0));
            jsonResult.setSuccess(true);
            jsonResult.setMsg("删除成功");
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }
    @Override
    public Purchaseaddress getModel() {
        return this.purchaseaddress;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public PurchaseaddressQueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(PurchaseaddressQueryParam queryParam) {
        this.queryParam = queryParam;
    }
}
