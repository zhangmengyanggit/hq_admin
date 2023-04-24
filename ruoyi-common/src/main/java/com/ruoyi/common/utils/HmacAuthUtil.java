package com.ruoyi.common.utils;

import javafx.util.Pair;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HmacAuthUtil {


    final static String ak = "BCDSGA_9e89b9d93e76349a8e76576e03f17172";
    final static String sk = "BCDSGS_dcc8d36ef9c8778e8ecf6b6bbd38e70d";

    final static String URL_PREFIX = "https://drs.sc.yichang.gov.cn/api/restapi/prod";


    public static String buildUrl(String url){
        return URL_PREFIX + url;
    }

    /**
     * 构造http请求 header
     *
     * @param url           请求url，全路径格式，比如：https://bcdsg.zj.gov.cn/api/p/v1/user.get
     * @param requestMethod 请求方法,大写格式，如：GET, POST
     * @return
     */
    public static Map<String, String> generateHeader(String url, String requestMethod) {
        String accessKey = ak;
        String secretKey = sk;
        Map<String, String> header = new HashMap<>();
        try {
            DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String date = dateFormat.format(new Date());
            URI uri = URI.create(buildUrl(url));
            String canonicalQueryString = getCanonicalQueryString(uri.getQuery());
            String message = requestMethod.toUpperCase() + "\n" + uri.getPath() + "\n" + canonicalQueryString + "\n" + accessKey + "\n" + date + "\n";
            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"));
            byte[] hash = hasher.doFinal(message.getBytes());
            // to lowercase hexits
            DatatypeConverter.printHexBinary(hash);
            // to base64
            String sign = DatatypeConverter.printBase64Binary(hash);
            header.put("X-BG-HMAC-SIGNATURE", sign);
            header.put("X-BG-HMAC-ALGORITHM", "hmac-sha256");
            header.put("X-BG-HMAC-ACCESS-KEY", accessKey);
            header.put("X-BG-DATE-TIME", date);
        } catch (Exception e) {
            throw new RuntimeException("generate header error");
        }
        return header;
    }


    private static String getCanonicalQueryString(String query) {
        if (query == null || query.trim().length() == 0) {
            return "";
        }
        List<Pair<String, String>> queryParamList = new ArrayList<>();
        String[] params = query.split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            Pair<String, String> pair = new Pair<String, String>(keyValue[0], keyValue.length > 1 ? keyValue[1] : "");
            queryParamList.add(pair);
        }
        List<Pair<String, String>> sortedParamList = queryParamList.stream().sorted(Comparator.comparing(param -> param.getKey() + "=" + Optional.ofNullable(param.getValue()).orElse(""))).collect(Collectors.toList());
        List<Pair<String, String>> encodeParamList = new ArrayList<>();
        sortedParamList.stream().forEach(param -> {
            try {
                String key = URLEncoder.encode(param.getKey(), "utf-8");
                String value = URLEncoder.encode(Optional.ofNullable(param.getValue()).orElse(""), "utf-8");
                encodeParamList.add(new Pair<>(key, value));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("encoding error");
            }
        });
        StringBuilder queryParamString = new StringBuilder(64);
        for (Pair<String, String> encodeParam : encodeParamList) {
            queryParamString.append(encodeParam.getKey()).append("=").append(Optional.ofNullable(encodeParam.getValue()).orElse(""));
            queryParamString.append("&");
        }
        return queryParamString.substring(0, queryParamString.length() - 1);
    }
}