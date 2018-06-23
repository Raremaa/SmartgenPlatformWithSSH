package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Product;
import com.zing.queryparam.ProductQueryParam;
import com.zing.serviceDao.ProductServiceDao;
import com.zing.util.JsonResultForMapUtil;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

    private Product product = new Product();
    private Map<String,Object> datas = new HashMap<String,Object>(0);
    private ProductQueryParam queryParam;//产品表通用查询接口数据
    @Autowired
    private ProductServiceDao productServiceDao;

    /**
     * 条件查询
     * 基于产品表通用查询接口数据
     */
    @JSON(serialize = false)
    public String getProductList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Product> list = productServiceDao.getProductList(queryParam);
            jsonResult.setMsg("查询成功");
            jsonResult.setSuccess(true);
            jsonResult.setTotal(list.size());
            jsonResult.setDatas(list);
            jsonResult.setPage(queryParam.getPage());
            jsonResult.setPageSize(queryParam.getPageSize());
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }
        JsonResultForMapUtil.packageClass(datas,jsonResult);
        return SUCCESS;
    }

    /**
     * 保存
     */
    public String save(){
        JsonResult jsonResult = new JsonResult();
        try {
            productServiceDao.save(this.product);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("成功");
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }
    @Override
    public Product getModel() {
        return this.product;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public ProductQueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(ProductQueryParam queryParam) {
        this.queryParam = queryParam;
    }
}
