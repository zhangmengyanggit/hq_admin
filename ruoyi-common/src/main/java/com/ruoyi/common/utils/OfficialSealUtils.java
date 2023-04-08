package com.ruoyi.common.utils;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.surpass.hbzzapi.HbzzApi;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OfficialSealUtils {
    public static void getOfficialSeal(String resourceFilePath,String targetFilePathOfd) {
        String userId = "yabzglxt";//调用接口账号
        String apiKey = "5b311ec05350aa0f7b47cad8b48f9b4c";//调用接口APIKEY,请妥善保管
        String sealid = "42050500018925";//42052500013753 地税局//42050500018925 宜昌市猇亭区商务局
        //正式环境地址(一般调用接口使用以下地址)
        String apiAddress = "http://yczwfw.yichang.gov.cn:8010";
        Map<String, Object> map = new HashMap();
        map.put("stampedPages", "1");
        map.put("x", "90");
        map.put("y", "70");
        try {
            HbzzApi api = HbzzApi.createInstance(apiAddress, userId, apiKey);
            Map<String, String> param = new HashMap<>();
            param.put("sealid", sealid);//如图1所示的参数
            OfdUtil.pdfToOfd(resourceFilePath, targetFilePathOfd);
            param.put("ofdStr", readFromFile(targetFilePathOfd));//如图1所示的参数
            param.put("encodeFlag", "1");
            param.put("signConfigJson", JSONObject.toJSONString(map));//如图1所示的参数
            //没有参数则传递一个实例map即可
            String result = new String(api.GetApi("/ythptdzzz/api/dzzz/stamp4CityDzzzByOfdBase64", HbzzApi.HttpMethod.POST, param), "UTF-8");  //调用方法路径如图1所示“接口地址”栏
            JSONObject aa = JSONObject.parseObject(result);
            JSONObject data = aa.getJSONObject("data");
            String ofdFileName = Base64Util.base64ToFile(data.getString("ofdBase64"));
            OfdUtil.ofdToPdf(ofdFileName, resourceFilePath);
            //清除ofd文件
           File f=new File(ofdFileName);
           f.delete();
            //清除ofd文件
            File file=new File(targetFilePathOfd);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从文件中读取文本
     */
    public static String readFromFile(String filename) {

        BufferedInputStream bufferedInput = null;
        String encode = null;
        try {
            //创建BufferedInputStream 对象
            bufferedInput = new BufferedInputStream(new FileInputStream(filename));
            encode = Base64.encode(bufferedInput);
            encode = Base64.encode(encode, "utf-8");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //关闭 BufferedInputStream
            try {
                if (bufferedInput != null)
                    bufferedInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return encode;
    }

    public static void main(String[] args) {
        getOfficialSeal("F:\\测试盖章\\宜昌市猇亭区关于印发.pdf","F:\\测试盖章\\001.ofd");
    }
}
