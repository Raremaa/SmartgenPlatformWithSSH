package com.zing.util;

import com.zing.json.JsonResult;

import java.util.Map;

/**
 * 将JsonResult类封装为Map
 */
public class JsonResultForMapUtil {

    public static void packageClass(Map map, JsonResult jsonResult){
        map.put("total",jsonResult.getTotal());
        map.put("page",jsonResult.getPage());
        map.put("pageSize",jsonResult.getPageSize());
        map.put("success",jsonResult.getSuccess());
        map.put("msg",jsonResult.getMsg());
        map.put("datas",jsonResult.getDatas());
    }
}
