package com.zing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.qiniu.util.Auth;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller("qiNiuAction")
@Scope("prototype")
public class QiNiuAction extends ActionSupport {

    private Map<String,Object> datas = new HashMap<String,Object>(0);


    @JSON(serialize = false)
    public String getToken(){
        String accessKey = "tENTHr-AKjugTECRTk5pCeRsbYxkGkaAnsbeZav5";
        String secretKey = "Pcj1-qAzkIBBHsbS0RylxRB5wA-1D7MaSzj2ds4h";
        String bucket = "smartgenplatform"; // 七牛空间名（改为自己的）
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket); // 生成普通上传的Token
        datas.put("upToken",upToken);
        return SUCCESS;
    }
    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }
}
