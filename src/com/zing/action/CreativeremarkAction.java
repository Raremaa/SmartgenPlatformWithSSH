package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zing.json.JsonResult;
import com.zing.pojo.Creativeremark;
import com.zing.serviceDao.CreativeremarkServiceDao;
import com.zing.util.JsonResultForMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("creativeremarkAction")
@Scope("prototype")
public class CreativeremarkAction extends ActionSupport{

    private Map<String,Object> datas = new HashMap<String,Object>(0);
    @Autowired
    private CreativeremarkServiceDao creativeremarkServiceDao;
    private Integer uId;//用户表主键
    private Integer creProjectId;//创意项目表主键

    public String getByUserAndCreProject(){
        JsonResult jsonResult = new JsonResult();
        try {
            if(uId == null){
                jsonResult.setMsg("用户主键为空");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            if (creProjectId == null){
                jsonResult.setMsg("创意项目表主键为空");
                JsonResultForMapUtil.packageClass(datas,jsonResult);
                return SUCCESS;
            }
            Creativeremark creativeremark = creativeremarkServiceDao.getByUserAndCreProject(uId,creProjectId);
            if(creativeremark == null){
                jsonResult.setMsg("该用户尚未对此项目评论/点赞");
                jsonResult.setTotal(0);
                jsonResult.setPageSize(0);
                jsonResult.setSuccess(true);
            }
            List<Creativeremark> list = new ArrayList<Creativeremark>(0);
            list.add(creativeremark);
            jsonResult.setSuccess(true);
            jsonResult.setDatas(list);
            jsonResult.setMsg("查询成功");
            jsonResult.setPageSize(list.size());
            jsonResult.setTotal(list.size());
        } catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }
    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getCreProjectId() {
        return creProjectId;
    }

    public void setCreProjectId(Integer creProjectId) {
        this.creProjectId = creProjectId;
    }
}
