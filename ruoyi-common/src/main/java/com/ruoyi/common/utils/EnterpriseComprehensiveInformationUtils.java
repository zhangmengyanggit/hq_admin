package com.ruoyi.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.isoftstone.sign.SignGeneration;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.http.HttpUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterpriseComprehensiveInformationUtils {
    private static final Logger log = LoggerFactory.getLogger(LegalPersonDatabaseUtils.class);

    /**
    *param certificateNumber(证书编号)
     param enterpriseName(企业名称)
     param unifyCode(统一社会信用代码)
     param dwmc(单位名称)
     param xmbh(项目编号)
     param xmmc(项目名称)
     param type(类型)
    * */
     public  static String  getEnterpriseComprehensiveInformationResult(String serviceId,String url,String certificateNumber,String enterpriseName,String unifyCode,String dwmc,String xmbh,String xmmc,Integer type){
         try {
             Map<String,String > paramsMap=new HashMap<>();
             paramsMap.put("serviceId",serviceId);
             paramsMap.put("ak", RuoYiConfig.getAk());
             paramsMap.put("appId",RuoYiConfig.getAppId());
             //获取当前时间的时间

             paramsMap.put("timestamp",DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSS));
             if(type.equals(2)){
                 if(StringUtils.isNotEmpty(certificateNumber)){
                     paramsMap.put("certificateNumber",certificateNumber);
                 }
                 if(StringUtils.isNotEmpty(enterpriseName)){
                     paramsMap.put("enterpriseName",enterpriseName);
                 }
                 if(StringUtils.isNotEmpty(unifyCode)){
                     paramsMap.put("unifyCode",unifyCode);
                 }
             }else if(type.equals(1)){
                 if(StringUtils.isNotEmpty(dwmc)){
                     paramsMap.put("dwmc",dwmc);
                 }
             } else if(type.equals(3)){
                 if(StringUtils.isNotEmpty(xmbh)){
                     paramsMap.put("xmbh",xmbh);
                 }
                 if(StringUtils.isNotEmpty(xmmc)){
                     paramsMap.put("xmmc",xmmc);
                 }
             }
             String sign=   SignGeneration.generationSign(paramsMap,RuoYiConfig.getSk());
             paramsMap.put("sign",sign);

             String result= HttpUtils.sendPost(url,paramsMap);
             //解密
            // SM4Utils sm4=new SM4Utils();
             //sm4.secretKey=RuoYiConfig.getSk().substring(0,16);
             //String plainText=sm4.decryptData_ECB(result);
             JSONObject object=   JSONObject.parseObject(result);
             String code=object.getString("code");
             if(code.equals("200")){
                 String dataStr=object.getString("data");
                 log.info("dataStr:"+dataStr);
                 return dataStr;
             }
         } catch (Exception e) {
             log.info("企业综合信息查询异常：{}",e.getMessage());
         }
         return null;
     }

    public static void main(String[] args) {
           String data="{\"code\":\"200\",\"msg\":\"成功\",\"data\":\"{\\\"code\\\":0,\\\"data\\\":\\\"<?xml version=\\\\\\\"1.0\\\\\\\" encoding=\\\\\\\"UTF-8\\\\\\\" standalone=\\\\\\\"yes\\\\\\\"?>\\\\n<ROOT>\\\\n    <rtnCode>000000</rtnCode>\\\\n    <rtnMsg>请求成功</rtnMsg>\\\\n    <gqData>\\\\n        <year>2019</year>\\\\n        <enterpriseName>宜昌兴越新材料有限公司</enterpriseName>\\\\n        <unifyCode>91420500309804832J</unifyCode>\\\\n        <certificateNumber>GR201942001291</certificateNumber>\\\\n        <certificateStatus>有效</certificateStatus>\\\\n        <determinationDate>2019-11-15 12:00:00</determinationDate>\\\\n        <industry>制造业</industry>\\\\n        <province>湖北省</province>\\\\n        <city>宜昌市</city>\\\\n        <distinct>猇亭区</distinct>\\\\n        <identificationAuthority>湖北省科学技术厅,湖北省财政厅,国家税务总局湖北省税务局</identificationAuthority>\\\\n    </gqData>\\\\n</ROOT>\\\\n\\\"}\"}\n";
        JSONObject object=   JSONObject.parseObject(data);
        String code=object.getString("code");
        if(code.equals("200")){
            String dataStr=object.getString("data");
            JSONObject objData= JSONObject.parseObject(dataStr);
            String xmlData=objData.getString("data");
            try {
                Document doc = DocumentHelper.parseText(xmlData);// 报文转成doc对象
                Element rootElement= doc.getRootElement();
                Element  gqDataElement= rootElement.element("gqData");
                Element yearElement=  gqDataElement.element("year");
                String year=yearElement.getText();
                Element certificateStatusElement=  gqDataElement.element("certificateStatus");
                String certificateStatus=certificateStatusElement.getText();
                Element enterpriseNameElement=  gqDataElement.element("enterpriseName");
                String enterpriseName=enterpriseNameElement.getText();
                Element unifyCodeElement=  gqDataElement.element("unifyCode");
                String unifyCode=unifyCodeElement.getText();
                Element certificateNumberElement=  gqDataElement.element("certificateNumber");
                String certificateNumber=certificateNumberElement.getText();

                Element determinationDateElement=  gqDataElement.element("determinationDate");
                String determinationDate=determinationDateElement.getText();
                Element industryElement=  gqDataElement.element("industry");
                String industry=industryElement.getText();
                Element identificationAuthorityElement=  gqDataElement.element("identificationAuthority");
                String identificationAuthority=identificationAuthorityElement.getText();
                Element provinceElement=  gqDataElement.element("province");
                String province=provinceElement.getText();
                Element cityElement=  gqDataElement.element("city");
                String city=cityElement.getText();
                Element distinctElement=  gqDataElement.element("distinct");
                String distinct=distinctElement.getText();


            } catch (DocumentException e) {
                e.printStackTrace();
            }
            //xmlData=XMLUtils.convertToXml(xmlData);
            //Document documentData= XMLUtils.strToDoc(xmlData);
            //NodeList rootNodeList= documentData.getElementsByTagName("ROOT");
            //for (int i=0;i<rootNodeList.getLength();i++ ) {
            // Node node=   rootNodeList.item(i);
            //  String value=  node.getNodeValue();
              //System.out.println(value);
            //}
        }
    }
}
