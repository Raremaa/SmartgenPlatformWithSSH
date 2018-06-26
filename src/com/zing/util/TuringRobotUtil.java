package com.zing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 图灵机器人接入工具类
 * apikey：6522b18404f143629685c8268bfedbf6
 * 官方网址:http://www.tuling123.com
 */
public class TuringRobotUtil{

        public static String doApi(String inputText)throws Exception{
            //申请图灵机器人后获取
            String APIKEY = "6522b18404f143629685c8268bfedbf6";
            String question = inputText;// 这是上传给云机器人的问题
            String INFO = URLEncoder.encode(question, "utf-8");
            String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY+ "&info=" + INFO;
            URL getUrl = new URL(getURL);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            // 取得输入流，并使用Reader读取
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        }
}
