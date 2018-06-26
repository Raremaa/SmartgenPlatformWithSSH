package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zing.json.JsonResult;
import com.zing.util.JsonResultForMapUtil;
import com.zing.util.TuringRobotUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.*;


@Controller("turingRobotAction")
@Scope("prototype")
public class TuringRobotAction extends ActionSupport {

    private String question;
    private Map<String,Object> datas = new HashMap<String,Object>(0);

    public String doRobot()throws Exception{
        JsonResult jsonResult = new JsonResult();
        if(question == null || question.equals("")){
            jsonResult.setMsg("您还没说问题呢！");
        }else{
            String temp = TuringRobotUtil.doApi(question);
            List<String> list = new ArrayList<String>(0);
            list.add(temp);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("回调成功！");
            jsonResult.setDatas(list);
        }
        JsonResultForMapUtil.packageClass(datas,jsonResult);
        return SUCCESS;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
