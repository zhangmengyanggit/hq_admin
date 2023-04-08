package com.ruoyi.common.utils;

import cn.hutool.core.util.CharsetUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

/**
 * @author LHW
 */
public class Base64Util {

    /**
     * BASE64解密
     */
    public static byte[] decryptBase64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     */
    public static String encryptBase64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * base64字符串转文件
     */
    public static String base64ToFile(String base64) {

        String fileName = "F:\\" + DateUtils.parseDateToStr("yyyyMMddhhmmss", new Date()) + System.currentTimeMillis() + "_tmp.ofd";

        byte base64Str[] = cn.hutool.core.codec.Base64.decode(base64);
        base64Str = cn.hutool.core.codec.Base64.decode(base64Str);
        try {
            //创建一个文件字节输出流
            FileOutputStream out = new FileOutputStream(fileName);
            byte[] b = base64Str;
            for (int i = 0; i < b.length; i++) {
                out.write(b[i]);
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;

    }


    /**
     * 文件转base64字符串
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();

            base64 = Base64.getEncoder().encodeToString(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64;
    }

    /**
     * base64 转 文件
     *
     * @param ofdFilePath
     * @param fileName
     */
    public static void stringToOfd(String base64, String ofdFilePath, String fileName) {

        byte[] decodeBase64 = cn.hutool.core.codec.Base64.decode(base64);


        if (decodeBase64 != null) {
            File directory = new File(ofdFilePath);
            if (!directory.exists() || !directory.isDirectory()) {
                directory.mkdir();
            }
            //String filepath =ofdFilePath + fileName;
            File file = new File(ofdFilePath + fileName);
            if (file.exists()) {
                file.delete();
            }

            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(decodeBase64, 0, decodeBase64.length);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将file文件转换成Byte数组
     *
     * @param file 转换文件
     * @return Byte数组
     */
    public static byte[] getBytesByFile(File file) throws IOException {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
        try {
            fis = new FileInputStream(file);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            byte[] data = bos.toByteArray();
            return data;
        } catch (Exception e) {
        } finally {
            if (fis != null) {
                fis.close();
            }
            bos.close();
        }
        return null;
    }


}
 