package com.zing.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成订单编号工具类
 * 算法:当前时间(按照"yyyyMMddHHmmss"格式)+用户id
 * 这个算法比较粗糙 后续优化
 */
public class PurchaseNoGenerate {

    public static String doGenerate(Date date,Integer userId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(date);
        String result = (dateStr +userId);
        return result;
    }
}
