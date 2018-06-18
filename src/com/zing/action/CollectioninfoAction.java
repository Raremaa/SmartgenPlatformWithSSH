package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Collectioninfo;
import com.zing.queryparam.CollectioninfoQueryParam;
import com.zing.serviceDao.CollectioninfoServiceDao;
import com.zing.util.JsonResultForMapUtil;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("collectioninfoAction")
@Scope("prototype")
public class CollectioninfoAction extends ActionSupport implements ModelDriven<Collectioninfo> {

    private Collectioninfo collectioninfo = new Collectioninfo();
    private Map<String,Object> datas = new HashMap<String,Object>(0);
    private CollectioninfoQueryParam queryParam;
    @Autowired
    private CollectioninfoServiceDao collectioninfoServiceDao;

    @JSON(serialize = false)
    public String findList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Collectioninfo> list = collectioninfoServiceDao.getList(this.queryParam);
            jsonResult.setMsg("查询成功");
            jsonResult.setTotal(list.size());
            jsonResult.setDatas(list);
            jsonResult.setPageSize(list.size());
            jsonResult.setSuccess(true);
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    public String save(){
        JsonResult jsonResult = new JsonResult();
        try {
            if (this.collectioninfo == null){
                jsonResult.setMsg("待添加收藏信息为空");
            }else{
                if(collectioninfo.getUser() == null){
                    jsonResult.setMsg("用户信息不可为空！");
                }else {
                    if(collectioninfo.getProduct() == null){
                        jsonResult.setMsg("产品信息不可为空");
                    }else{
                        Integer flag = collectioninfoServiceDao.save(this.collectioninfo);
                        jsonResult.setMsg("添加成功");
                        jsonResult.setSuccess(true);
                        jsonResult.setTotal(1);
                        jsonResult.setPageSize(1);
                    }
                }
            }
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }



    @Override
    public Collectioninfo getModel() {
        return this.collectioninfo;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public CollectioninfoQueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(CollectioninfoQueryParam queryParam) {
        this.queryParam = queryParam;
    }
}
