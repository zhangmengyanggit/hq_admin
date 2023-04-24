package com.ruoyi.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 法人库调取工具类
 *
 * @author zmy
 */
public class LegalPersonDatabaseUtils {
    private static final Logger log = LoggerFactory.getLogger(LegalPersonDatabaseUtils.class);

    public static String getLegalPersonDatabase() {
        String result = null;
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            long timestamp = System.currentTimeMillis() / 1000;
            //按照字典对参数进行排序
          /*  Map<String, String> params = new TreeMap<>();
            params.put("access_key", RuoYiConfig.getAccessKey());
            params.put("timestamp", String.valueOf(timestamp));
            String signString = params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
            //对参数进行加密
            String signature = org.apache.commons.codec.digest.HmacUtils.hmacSha256Hex(RuoYiConfig.getSecretKey(), signString);
            //获取动态token
            URIBuilder uriBuilder = new URIBuilder("https://drs.sc.yichang.gov.cn/api/apisix/token");
            uriBuilder.addParameter("access_key", RuoYiConfig.getAccessKey());
            uriBuilder.addParameter("timestamp", String.valueOf(timestamp));
            uriBuilder.addParameter("signature", signature);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            HttpResponse response = httpClient.execute(httpGet);
            String content = EntityUtils.toString(response.getEntity());
            if (response.getStatusLine().getStatusCode() != 200) {
                log.error(content);
                throw new RuntimeException();
            }

            //解析token返回结果 示例:{"token": "", "expire_time": 0, "error_msg": ""}
            JSONObject jsonObject = new JSONObject(content);
            log.info("jsonObject={}",jsonObject);
            String token = jsonObject.has("token") ? jsonObject.getString("token") : null;
            //token过期时间
            long expireTime = jsonObject.has("expire_time") ? jsonObject.getLong("expire_time") : 0L;
            String errorMsg = jsonObject.has("error_msg") ? jsonObject.getString("error_msg") : null;
            if (errorMsg != null && errorMsg.length() != 0) {
                log.error(errorMsg);
                throw new RuntimeException();
            }*/

            //进行接口调用
            /*String requestBody = "";//实际调用json参数
            RequestBuilder requestBuilder = RequestBuilder.create("POST")
                    .addHeader("X-ACCESS-TOKEN", token)
                    .addHeader("Content-Type", "application/json")
                    .setUri(new URIBuilder(RuoYiConfig.getUrlSelectAll()).build())
                    .setEntity(new StringEntity(requestBody, "utf-8"));
            log.info("url={}",RuoYiConfig.getUrlSelectAll());
            HttpUriRequest request =requestBuilder.build();
            response = httpClient.execute(request);
            content = EntityUtils.toString(response.getEntity());*/
            Long pageSize = SpringUtils.getBean(RedisCache.class).getCacheObject(CacheConstants.LEGAL_PERSON_DATABASE_COUNT);
            pageSize = pageSize == null ? 31605 : pageSize;
            log.info("pageSize={}", pageSize);
            String json = "{\n" + "\"params\":{\n" + " \"pripid\":\"142000000080486108\"   \n" + "},\n" + "\"pageNum\":\"1\",\n" + "\"pageSize\":\"%s\"\n" + "}";
            json = String.format(json, pageSize);
            // 请求地址
            String url = "/IC44300020230403000002/xdata/tbl/api/execute/16fab83e-efe3-488b-b18e-8b3065b610d5";
            // 请求方式
            Method method = Method.POST;

            // 构建网关鉴权信息
            Map<String, String> header = HmacAuthUtil.generateHeader(url, method.name());

            // 远程接口调用
            cn.hutool.http.HttpResponse httpResponse = HttpUtil.createRequest(method, HmacAuthUtil.buildUrl(url)).headerMap(header, true)
//                .body(JSONObject.toJSONString(params))
                    .body(json).execute();
            int status = httpResponse.getStatus();
            String body = httpResponse.body();
            //log.info("content={}", body);
            JSONObject objectContent = com.alibaba.fastjson.JSONObject.parseObject(body);
            if (objectContent.getBooleanValue("success")) {
                JSONObject dataJSONObject = objectContent.getJSONObject("data");
                String listStr = dataJSONObject.getString("list");
                Long totalItem = dataJSONObject.getLong("totalItem");
                //log.info("totalItem={}", totalItem);
                //总数放入缓存
                SpringUtils.getBean(RedisCache.class).setCacheObject(CacheConstants.LEGAL_PERSON_DATABASE_COUNT, totalItem, 1, TimeUnit.DAYS);
                result = listStr;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return result;
    }

    public static void main(String[] args) {
       /* com.alibaba.fastjson.JSONObject  object=new com.alibaba.fastjson.JSONObject();
        object.put("mode","raw");
        com.alibaba.fastjson.JSONObject  objectRaw=new com.alibaba.fastjson.JSONObject();
        objectRaw.put("params",new HashMap<>());
        objectRaw.put("pageNum","1");
        objectRaw.put("pageSize","10");
        object.put("raw",objectRaw);
        com.alibaba.fastjson.JSONObject  objectOptions=new com.alibaba.fastjson.JSONObject();
        Map<String,String> map=new HashMap<>();
        map.put("language","json");
        objectOptions.put("raw",map);
        object.put("options",objectOptions);
        System.out.println(object);*/
        // getLegalPersonDatabase();
        String str = "小超%s";
        str = String.format(str, 111);
        System.out.println(str);
    }
}
