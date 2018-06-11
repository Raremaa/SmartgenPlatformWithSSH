package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.User;
import com.zing.serviceDao.UserServiceDao;
import com.zing.util.JsonResultForMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

    private User user = new User();
    private String condition;
    @Autowired
    private UserServiceDao userServiceDao;
    private Map<String,Object> datas = new HashMap<String,Object>(0);

    /**
     * 通过id查询用户信息(不包含关联数据)
     */
    public String getUserById() throws Exception {
        JsonResult jsonResult = new JsonResult();
        List<User> list = new ArrayList<User>(0);
        User user = userServiceDao.getUserById(this.user.getId());
        if(user == null){
            jsonResult.setMsg("没有该用户");
        }else {
            list.add(user);
            jsonResult.setDatas(list);
            jsonResult.setSuccess(true);
            jsonResult.setTotal(1);
            jsonResult.setPageSize(1);
            jsonResult.setMsg("查询成功");
        }
        JsonResultForMapUtil.packageClass(datas,jsonResult);
        return SUCCESS;
    }

    /**
     * 登录验证
     * 返回用户相关信息
     */
    public String login() throws Exception {
        JsonResult jsonResult = new JsonResult();
        List<User> list = userServiceDao.getUserList(this.condition);
        if(list.size() == 0){
            jsonResult.setMsg("用户不存在或密码错误");
        }else{
            jsonResult.setSuccess(true);
            jsonResult.setDatas(list);
            jsonResult.setTotal(1);
            jsonResult.setPageSize(1);
            jsonResult.setMsg("登录成功");
        }
        JsonResultForMapUtil.packageClass(datas,jsonResult);
        return SUCCESS;
    }

    /**
     * 注册用户
     * 后台进行查重 设头像默认值 必填项判断处理 插入操作
     * 前台这里要负责控制长度满足数据库约束
     */
    public String register() throws Exception{
        JsonResult<User> jsonResult = new JsonResult<User>();
        //判断必填项是否为空 并进行处理
        if (user.getUserName() == null) {
            jsonResult.setMsg("用户名不可为空!");
        }else if(user.getUserPhone() == null){
            jsonResult.setMsg("手机号不可为空!");
        }else if(user.getUserPassword() == null){
            jsonResult.setMsg("密码不可为空!");
        }else if(user.getUserHeadPortrait() == null){
            user.setUserHeadPortrait("defaultJpg");//默认头像
        }else if(user.getUserIdentity() == null){
            user.setUserIdentity(0);//默认 0-未认证
        }else{
            //验证手机号是否已经注册
            List<User> list = userServiceDao.getUserList("userPhone = "+user.getUserPhone());
            if (list.size()!=0) {
                jsonResult.setMsg("您的手机号已注册！");
            } else {
                //进行插入
                Integer id = userServiceDao.save(user);
                if(id == null || id == 0){
                    jsonResult.setMsg("注册失败,请联系众智客服！给您带来不好的体验深感歉意");
                }else {
                    jsonResult.setMsg("注册成功");
                    jsonResult.setTotal(1);
                    jsonResult.setPageSize(1);
                    jsonResult.setSuccess(true);
                }
            }
        }
        JsonResultForMapUtil.packageClass(datas,jsonResult);
        return SUCCESS;
    }

    @Override
    public User getModel() {
        return this.user;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
