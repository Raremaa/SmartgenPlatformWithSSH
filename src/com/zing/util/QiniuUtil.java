package com.zing.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

public class QiniuUtil {

	// 在七牛注册后获得的accessKey和secretKey（改为自己的）
	private static String accessKey = "tENTHr-AKjugTECRTk5pCeRsbYxkGkaAnsbeZav5";
	private static String secretKey = "Pcj1-qAzkIBBHsbS0RylxRB5wA-1D7MaSzj2ds4h";
	private static String bucket = "smartgenplatform"; // 七牛空间名（改为自己的）
	
	public static String getToken(){
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket); // 生成普通上传的Token
		return upToken;
	}
	
	public static void delFile(String key){
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		Auth auth = Auth.create(accessKey, secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
		    bucketManager.delete(bucket, key);
		} catch (QiniuException ex) {
		    //如果遇到异常，说明删除失败
		    System.err.println(ex.code());
		    System.err.println(ex.response.toString());
		}
	}
	
}
