package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Collectioninfo;
import com.zing.pojo.Product;
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
    private Integer userId;
    @Autowired
    private CollectioninfoServiceDao collectioninfoServiceDao;

    @JSON(serialize = false)
    /**
     * 条件查询
     * 暂未实现 后续实现
     */
    public String findList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Object[]> list = collectioninfoServiceDao.getList(this.queryParam);
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

    /**
     * 保存
     * @return
     */
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

    /**
     * 根据条件查询符合条件的数据总条数
     * 基于收藏表通用查询接口数据
     */
    @JSON(serialize = false)
    public String getCount(){
        JsonResult jsonResult = new JsonResult();
        try {
            Long temp = collectioninfoServiceDao.getCount(queryParam);
            jsonResult.setMsg("查询成功");
            jsonResult.setSuccess(true);
            jsonResult.setTotal(temp.intValue());
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /**
     * 根据用户id获取用户收藏的商品
     */
    public String getProductByUserId(){
        JsonResult jsonResult = new JsonResult();
        try {
            if(this.userId == null){
                jsonResult.setMsg("用户信息未指定！");
            }else {
                List<Product> list = collectioninfoServiceDao.getProductByUserId(this.userId);
                jsonResult.setMsg("查询成功");
                jsonResult.setSuccess(true);
                jsonResult.setTotal(list.size());
                jsonResult.setDatas(list);
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
