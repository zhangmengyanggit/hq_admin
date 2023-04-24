package com.ruoyi.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.AuthCache;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LinkSMS {
	private static final Logger log = LoggerFactory.getLogger(LinkSMS.class);
	  private static String CorpID = "XAJT001908";// 接口账户名
	  
	  private static String Pwd = "21C954";// 接口密码
	  

  public static String sendMSM(String mobile,String content) {
  		    String result="";
       	try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			AuthCache authCache = new BasicAuthCache();
			HttpClientContext context = HttpClientContext.create();
			context.setAuthCache (authCache);
			//2.封装短信接口参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();  
			params.add(new BasicNameValuePair("CorpID", CorpID));
			params.add(new BasicNameValuePair("Pwd",Pwd));
			params.add(new BasicNameValuePair("Mobile", mobile));
			params.add(new BasicNameValuePair("Content", content));
			params.add(new BasicNameValuePair("Cell", ""));
			params.add(new BasicNameValuePair("SendTime", ""));
			HttpEntity entity = new UrlEncodedFormEntity(params,"GBK");
			//3.提交访问地址
			HttpPost request = new HttpPost ("https://sdk3.028lk.com:9988/BatchSend2.aspx");
			request.setEntity(entity);
			//4.提交访问短信接口
			CloseableHttpResponse response = httpclient.execute(request);
			//5.获取返回值并解析
			HttpEntity entity2 = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(entity2.getContent(), "GBK"));  
            String line="";  
            while ((line = br.readLine()) != null) {  
            	result+= line+"\n";  
            }  
	        //4.关闭链接
            br.close();  
	        response.close ();
	        httpclient.close ();
       	} catch (Exception e) {
			log.info("短信发送异常:{}",e.getMessage());
       		e.getLocalizedMessage();
   		}	
       	return result;
     }
      /**
      *main测试
      */
    public static void main(String[] args) {
    	//您所在企业可以申请惠企业务,请复制链接进行查看:<a href='http://localhost/originalpolicyView?id='>Link</a>【快云科技】
    	String message="您所在企业可以申请惠企业务,如需登录，您的初始账号为您的手机号，初始密码为123456【快云科技】";
		String phone="18694083843";
		
		String result=LinkSMS.sendMSM(phone,message);
		System.out.println("----------------接口提交返回值-------------------"); 
	    System.out.print(result); 
	    System.out.println("----------------------------------------------");
	    //对返回值的判断。。。此处省略
	}
}
