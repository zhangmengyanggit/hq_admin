package com.ruoyi.common.utils;

import org.ofdrw.converter.ConvertHelper;
import org.ofdrw.converter.GeneralConvertException;
import org.ofdrw.converter.ofdconverter.PDFConverter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OfdUtil {

    /**
     * 将ofd转换为pdf
     * @param originPath 源文件路径
     * @param pdfPath 目标文件路径
     */
    public static void ofdToPdf(String originPath, String pdfPath) {
        // 1. 文件输入路径
        Path src = Paths.get(originPath);
        // 2. 转换后文件输出位置
        Path dst = Paths.get(pdfPath);
        try {
            // 3. OFD转换PDF
            ConvertHelper.toPdf(src, dst);
            System.out.println("生成文档位置: " + dst.toAbsolutePath());
        } catch (GeneralConvertException e) {
            // pom引入相关模块GeneralConvertException 类型错误表明转换过程中发生异常
            e.printStackTrace();
        }
    }



    /**
     * pdf转ofd
     * @param resourceFilePath PDF源文件地址
     * @param targetFilePath 需要输出ofd的目标地址
     */
    public static void pdfToOfd(String resourceFilePath, String targetFilePath){
        // 1. 文件输入路径
        Path src = Paths.get(resourceFilePath);
        // 2. 转换后文件输出位置
        Path dst = Paths.get(targetFilePath);
        try {
            try (PDFConverter converter = new PDFConverter(dst)) {
                converter.convert(src, 0, 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        OfdUtil.ofdToPdf("", "");
    }

}
