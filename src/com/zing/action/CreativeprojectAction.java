package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zing.json.JsonResult;
import com.zing.pojo.Creativeproject;
import com.zing.pojo.Creativeremark;
import com.zing.queryparam.CreQueryParam;
import com.zing.serviceDao.CreativeprojectServiceDao;
import com.zing.serviceDao.CreativeremarkServiceDao;
import com.zing.util.JsonResultForMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("creativeprojectAction")
@Scope("prototype")
public class CreativeprojectAction extends ActionSupport implements ModelDriven<Creativeproject> {

    private Creativeproject creativeproject = new Creativeproject();
    private Map<String,Object> datas = new HashMap<String,Object>(0);
    private CreQueryParam queryParam;//创意项目表通用查询接口数据
    private Integer uId;//用户表主键
    private Integer creProjectId;//创意项目表主键
    @Autowired
    private CreativeprojectServiceDao creativeprojectServiceDao;
    @Autowired
    private CreativeremarkServiceDao creativeremarkServiceDao;

    /**
     * 条件查询
     * 基于创意项目表通用查询接口数据
     */
    public String getCreativeprojectList(){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Creativeproject> list = creativeprojectServiceDao.getCreativeprojectList(queryParam);
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
     * 创意项目点赞
     */
    public String parise(){
        JsonResult jsonResult = new JsonResult();
        try {
            //查询待点赞创意项目是否存在
            Creativeproject creativeproject = creativeprojectServiceDao.getById(creProjectId);
            if(creativeproject == null){
                //如果不存在
                jsonResult.setMsg("待点赞创意项目不存在");
            }else{
                //如果存在
                //查询该用户对于该项目是否已经有过评论
                Creativeremark creativeremark = creativeremarkServiceDao.getByUserAndCreProject(this.uId,this.creProjectId);
                //如果有
                if(creativeremark != null) {
                    //如果创意项目评论表点赞状态未0(未点赞)
                    if(creativeremark.getCreremarkPraise() == 0) {
                        //修改点赞状态为1(点赞) 创意项目点赞数+1
                        creativeremark.setCreremarkPraise(1);
                        creativeremarkServiceDao.update(creativeremark);
                        if(creativeproject.getCreprojectPraise() != null){
                            creativeproject.setCreprojectPraise(creativeproject.getCreprojectPraise()+1);
                        }else{
                            creativeproject.setCreprojectPraise(1);
                        }
                        creativeprojectServiceDao.update(creativeproject);
                        jsonResult.setMsg("点赞成功");
                        jsonResult.setSuccess(true);
                        //如果创意项目评论表点赞状态为1(已点赞)
                    }else {
                        //修改点赞状态为0(未点赞) 创意项目点赞数-1
                        if(creativeproject.getCreprojectPraise() <= 0 || creativeproject.getCreprojectPraise() == null){
                            jsonResult.setMsg("创意项目点赞数数据错误 与创意项目点赞表互相矛盾");
                        }else{
                            creativeremark.setCreremarkPraise(0);
                            creativeremarkServiceDao.update(creativeremark);
                            creativeproject.setCreprojectPraise(creativeproject.getCreprojectPraise()-1);
                            creativeprojectServiceDao.update(creativeproject);
                            jsonResult.setMsg("取消点赞成功");
                            jsonResult.setSuccess(true);
                        }
                    }
                //如果没有
                }else{
                    //创意项目评论表添加一条记录 创意项目点赞数+1
                    Creativeremark temp = new Creativeremark();
                    temp.setCreremarkPraise(1);
                    temp.setCreprojectId(creProjectId);
                    temp.setUserId(uId);
                    creativeremarkServiceDao.save(temp);
                    if(creativeproject.getCreprojectPraise() != null){
                        creativeproject.setCreprojectPraise(creativeproject.getCreprojectPraise()+1);
                    }else {
                        creativeproject.setCreprojectPraise(1);
                    }
                    creativeprojectServiceDao.update(creativeproject);
                    jsonResult.setMsg("点赞成功");
                    jsonResult.setSuccess(true);
                }
            }
        }
        catch (Exception e) {
            jsonResult.setMsg(e.toString());
            e.printStackTrace();
        }finally {
            JsonResultForMapUtil.packageClass(datas,jsonResult);
            return SUCCESS;
        }
    }

    @Override
    public Creativeproject getModel() {
        return this.creativeproject;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public CreQueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(CreQueryParam queryParam) {
        this.queryParam = queryParam;
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
