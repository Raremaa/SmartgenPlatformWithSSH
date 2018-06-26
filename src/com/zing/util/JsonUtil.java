package com.zing.util;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * json解析工具类
 * 基于fastjson
 */
public class JsonUtil {

    /**
     * json字符串转化为Map对象
     */
    public static Map jsonToMap(String jsonString) throws Exception{
//        String str = "{\"age\":\"24\",\"name\":\"cool_summer_moon\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        //json对象转Map
        Map<String,Object> map = (Map<String,Object>)jsonObject;
        return map;
    }
}
