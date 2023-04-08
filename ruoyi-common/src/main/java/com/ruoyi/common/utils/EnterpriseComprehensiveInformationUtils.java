package com.ruoyi.common.utils;

import com.isoftstone.sign.SM4Utils;
import com.isoftstone.sign.SignGeneration;
import com.ruoyi.common.utils.http.HttpUtils;
import java.util.HashMap;
import java.util.Map;

public class EnterpriseComprehensiveInformationUtils {
     public  static String  getEnterpriseComprehensiveInformationResult(String serviceId,String ak,String appId,String sk,String url){
         try {
             Map<String,String > paramsMap=new HashMap<>();
             paramsMap.put("serviceId",serviceId);
             paramsMap.put("ak",ak);
             paramsMap.put("appId",appId);
             paramsMap.put("timestamp",System.currentTimeMillis()+"");
             String sign=   SignGeneration.generationSign(paramsMap,sk);
             System.out.println(sign);
             paramsMap.put("sign",sign);
             String result= HttpUtils.sendPost(url,paramsMap);
             System.out.println(result);
             //解密
             SM4Utils sm4=new SM4Utils();
             sm4.secretKey=sk.substring(0,16);
             String plainText=sm4.decryptData_ECB(result);
             System.out.println("明文："+plainText);
             return plainText;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }

    public static void main(String[] args) {
        try {
        Map<String,String > paramsMap=new HashMap<>();
        paramsMap.put("serviceId","ff8080818727de2901874a2028fa2f06");
        paramsMap.put("ak","fa55bfea988d433e907621e1c1c6b999");
        paramsMap.put("appId","527B81F3677C474C93285EB1A20E937C");
        paramsMap.put("timestamp",System.currentTimeMillis()+"");
            String sign=   SignGeneration.generationSign(paramsMap,"b9fed3e78e064c2e9d8429267784c5c9");
            System.out.println(sign);
            paramsMap.put("sign",sign);
            String result= HttpUtils.sendPost("http://data.hb.cegn.cn/irsp/openApi/kjbGxjsqyzsInfo/v1",paramsMap);
            System.out.println(result);
            //解密
            SM4Utils sm4=new SM4Utils();
            sm4.secretKey="b9fed3e78e064c2e9d8429267784c5c9";
            String plainText=sm4.decryptData_ECB(result);
            System.out.println("明文："+plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
