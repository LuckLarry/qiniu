package com.next;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class qiniuTool {
	 //设置好账号的ACCESS_KEY和SECRET_KEY
	 private String ACCESS_KEY;
	 private String SECRET_KEY;
	  //要上传的空间
	 private String bucketname ;
	  //上传到七牛后保存的文件名
	 private String key ;
	  //上传文件的路径
	 private String FilePath;
	public qiniuTool( String ACCESS_KEY,String SECRET_KEY,String bucketname,String key,String FilePath) {
			this.ACCESS_KEY = ACCESS_KEY;
			this.SECRET_KEY = SECRET_KEY;
			this.bucketname = bucketname;
			this.key = key;
			this.FilePath = FilePath;
					
	}
	  //密钥配置
	  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	  //创建上传对象
	  UploadManager uploadManager = new UploadManager();

	  //简单上传，使用默认策略，只需要设置上传的空间名就可以了
	  public String getUpToken(){
	      return auth.uploadToken(bucketname);
	  }

	  public void upload() throws IOException{
	    try {
	      //调用put方法上传
	      Response res = uploadManager.put(FilePath, key, getUpToken());
	      //打印返回的信息
	      System.out.println(res.bodyString()); 
	      } catch (QiniuException e) {
	          Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          try {
	              //响应的文本信息
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	              //ignore 
	          }
	      }       
	  }

	
 
	}
