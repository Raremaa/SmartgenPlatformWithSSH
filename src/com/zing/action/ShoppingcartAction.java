package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Product;
import com.zing.pojo.Shoppingcart;
import com.zing.pojo.User;
import com.zing.queryparam.ShoppingcartQueryParam;
import com.zing.serviceDao.ProductServiceDao;
import com.zing.serviceDao.ShoppingcartServiceDao;
import com.zing.serviceDao.UserServiceDao;
import com.zing.util.JsonResultForMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller("shoppingcartAction")
@Scope("prototype")
public class ShoppingcartAction extends ActionSupport implements ModelDriven<Shoppingcart> {

    private Shoppingcart shoppingcart = new Shoppingcart();
    private Map<String,Object> datas = new HashMap<String,Object>(0);
    @Autowired
    private ShoppingcartServiceDao shoppingcartServiceDao;
    @Autowired
    private UserServiceDao userServiceDao;
    @Autowired
    private ProductServiceDao productServiceDao;
    private ShoppingcartQueryParam queryParam = new ShoppingcartQueryParam();


    /**
     * 单用户添加单个产品到购物车
     */
    public String addShoppingcart(){
        JsonResult jsonResult = new JsonResult();
        try {
            //判断是否传入用户信息
            if (shoppingcart.getUser() == null || shoppingcart.getUser().getId() == null) {
                jsonResult.setMsg("未指定用户信息！");
                JsonResultForMapUtil.packageClass(datas, jsonResult);
                return SUCCESS;
            }
            //判断是否传入产品信息
            if (shoppingcart.getProduct() == null || shoppingcart.getProduct().getId() == null) {
                jsonResult.setMsg("未指定产品信息！");
                JsonResultForMapUtil.packageClass(datas, jsonResult);
                return SUCCESS;
            }
            //判断是否传入产品数量
            if (shoppingcart.getProductCount() ==null) {
                jsonResult.setMsg("未指定产品数量！");
                JsonResultForMapUtil.packageClass(datas, jsonResult);
                return SUCCESS;
            }
            //判断用户是否存在 同时生成用户对象
            User user = userServiceDao.getUserById(shoppingcart.getUser().getId());
            if (user == null) {
                jsonResult.setMsg("用户Id为["+shoppingcart.getUser().getId()+"]的用户不存在！");
                JsonResultForMapUtil.packageClass(datas, jsonResult);
                return SUCCESS;
            }
            //判断该商品是否存在在该用户的购物车表数据库中
            ShoppingcartQueryParam qTemp = new ShoppingcartQueryParam();
            qTemp.setCondition("user.id =" + shoppingcart.getUser().getId() + " and product.id =" + shoppingcart.getProduct().getId());
            List<Shoppingcart> list = shoppingcartServiceDao.getList(qTemp);
            Shoppingcart result = new Shoppingcart();
            //如果不在 则应该添加 如果不在 则应该修改
            if (list.size() == 0 || list == null) {
                //查询商品是否存在 同时将结果封装到购物车表的冗余字段里提高查询效率
                Product product = productServiceDao.getById(shoppingcart.getProduct().getId());
                if (product == null){
                    jsonResult.setMsg("商品Id为["+shoppingcart.getProduct().getId()+"]的商品不存在！");
                    JsonResultForMapUtil.packageClass(datas, jsonResult);
                    return SUCCESS;
                }
                result.setUser(user);
                result.setProduct(product);
                result.setProductCount(shoppingcart.getProductCount());
                result.setProductMsg(product.getProductOneMsg());
                result.setProductName(product.getProductName());
                result.setProductPicture(product.getProductPicture());
                result.setProductPrice(product.getProductPrice());
                shoppingcartServiceDao.save(result);
            } else {
                //在数据库中原商品数量的基础上加上所传的数量
                result = list.get(0);
                result.setProductCount(String.valueOf(Integer.valueOf(result.getProductCount()) + Integer.valueOf(shoppingcart.getProductCount())));
                shoppingcartServiceDao.update(result);
            }
            jsonResult.setMsg("添加成功");
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
     * 修改购物车里单个商品的数量
     */
    public String updateProductCount(){
        JsonResult jsonResult = new JsonResult();
        if(shoppingcart.getId() == null){
            jsonResult.setMsg("您未指定购物车信息！");
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
        ShoppingcartQueryParam qTemp = new ShoppingcartQueryParam();
        qTemp.setCondition("id =" + shoppingcart.getId() );
        try {
            List<Shoppingcart> list = shoppingcartServiceDao.getList(qTemp);
            if(list == null || list.size() == 0){
                jsonResult.setMsg("购物车信息不存在");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            Shoppingcart shoppingcartTemp = list.get(0);
            shoppingcartTemp.setProductCount(shoppingcart.getProductCount());
            shoppingcartServiceDao.update(shoppingcartTemp);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("修改成功");
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /**
     * 根据用户id查询购物车信息
     */
    public String getShoppingcart(){
        JsonResult jsonResult = new JsonResult();
        if(shoppingcart.getUser() == null || shoppingcart.getUser().getId() == null){
            jsonResult.setMsg("请传入用户信息");
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
        try {
            List<Shoppingcart> list = shoppingcartServiceDao.getListByUserId(shoppingcart.getUser().getId());
            jsonResult.setDatas(list);
            jsonResult.setMsg("查询成功");
            jsonResult.setSuccess(true);
            jsonResult.setTotal(list.size());
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    /**
     * 删除指定购物车信息
     */
    public String deleteShoppingcart(){
        JsonResult jsonResult = new JsonResult();
        if(shoppingcart.getId() == null){
            jsonResult.setMsg("您未指定待删除购物车信息");
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
        try {
            ShoppingcartQueryParam qTemp = new ShoppingcartQueryParam();
            qTemp.setCondition("id =" + shoppingcart.getId());
            List<Shoppingcart> list = shoppingcartServiceDao.getList(qTemp);
            if(list == null || list.size() == 0){
                jsonResult.setMsg("待删除购物车记录不存在");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            shoppingcartServiceDao.delete(list.get(0));
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

    public ShoppingcartQueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(ShoppingcartQueryParam queryParam) {
        this.queryParam = queryParam;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    @Override
    public Shoppingcart getModel() {
        return this.shoppingcart;
    }
}
