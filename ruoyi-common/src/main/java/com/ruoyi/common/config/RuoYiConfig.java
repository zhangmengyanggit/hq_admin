package com.ruoyi.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "ruoyi")
public class RuoYiConfig {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 实例演示开关
     */
    private static boolean demoEnabled;

    /**
     * 上传路径
     */
    private static String profile;

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    /**
     * 验证码类型
     */
    private static String captchaType;
    /**
     * severWebUrl
     */
    private static String severWebUrl;


    /**
     * 企业综合信息查
     * 询用到参数
     */
    private static String ak;
    private static String appId;
    private static String sk;
    /**
     * 科技部_高新技术企业证书信息
     */
    private static String serviceIdByEnterpriseCertificateInformation;
    private static String urlByEnterpriseCertificateInformation;
    /**
     * 科技部_高新技术企业备案基础信息查询接口
     */
    private static String serviceIdByBasicInformationForEnterpriseFiling;
    private static String urlByBasicInformationForEnterpriseFiling;
    /**
     * 科技部_科技型中小企业创新基金项目信息查询接口
     */
    private static String serviceIdBySMEInnovationFundProjectInformation;
    private static String urlBySMEInnovationFundProjectInformation;
    /**
     * 法人库
     */
    private static String AccessKey;
    private static String SecretKey;
    private static String urlSelectAll;
    private static String urlBySocialUnifiedCreditCodeNumber;


    public static String getAccessKey() {
        return AccessKey;
    }

    public  void setAccessKey(String accessKey) {
        AccessKey = accessKey;
    }

    public static String getSecretKey() {
        return SecretKey;
    }

    public  void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }

    public static String getUrlSelectAll() {
        return urlSelectAll;
    }

    public  void setUrlSelectAll(String urlSelectAll) {
        RuoYiConfig.urlSelectAll = urlSelectAll;
    }

    public static String getUrlBySocialUnifiedCreditCodeNumber() {
        return urlBySocialUnifiedCreditCodeNumber;
    }

    public  void setUrlBySocialUnifiedCreditCodeNumber(String urlBySocialUnifiedCreditCodeNumber) {
        RuoYiConfig.urlBySocialUnifiedCreditCodeNumber = urlBySocialUnifiedCreditCodeNumber;
    }

    public static String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        RuoYiConfig.ak = ak;
    }

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        RuoYiConfig.appId = appId;
    }

    public static String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        RuoYiConfig.sk = sk;
    }

    public static String getServiceIdByBasicInformationForEnterpriseFiling() {
        return serviceIdByBasicInformationForEnterpriseFiling;
    }

    public void setServiceIdByBasicInformationForEnterpriseFiling(String serviceIdByBasicInformationForEnterpriseFiling) {
        RuoYiConfig.serviceIdByBasicInformationForEnterpriseFiling = serviceIdByBasicInformationForEnterpriseFiling;
    }

    public static String getServiceIdBySMEInnovationFundProjectInformation() {
        return serviceIdBySMEInnovationFundProjectInformation;
    }

    public void setServiceIdBySMEInnovationFundProjectInformation(String serviceIdBySMEInnovationFundProjectInformation) {
        RuoYiConfig.serviceIdBySMEInnovationFundProjectInformation = serviceIdBySMEInnovationFundProjectInformation;
    }

    public static String getUrlByEnterpriseCertificateInformation() {
        return urlByEnterpriseCertificateInformation;
    }

    public void setUrlByEnterpriseCertificateInformation(String urlByEnterpriseCertificateInformation) {
        RuoYiConfig.urlByEnterpriseCertificateInformation = urlByEnterpriseCertificateInformation;
    }

    public static String getUrlByBasicInformationForEnterpriseFiling() {
        return urlByBasicInformationForEnterpriseFiling;
    }

    public void setUrlByBasicInformationForEnterpriseFiling(String urlByBasicInformationForEnterpriseFiling) {
        RuoYiConfig.urlByBasicInformationForEnterpriseFiling = urlByBasicInformationForEnterpriseFiling;
    }

    public static String getUrlBySMEInnovationFundProjectInformation() {
        return urlBySMEInnovationFundProjectInformation;
    }

    public void setUrlBySMEInnovationFundProjectInformation(String urlBySMEInnovationFundProjectInformation) {
        RuoYiConfig.urlBySMEInnovationFundProjectInformation = urlBySMEInnovationFundProjectInformation;
    }

    public static String getServiceIdByEnterpriseCertificateInformation() {
        return serviceIdByEnterpriseCertificateInformation;
    }

    public void setServiceIdByEnterpriseCertificateInformation(String serviceIdByEnterpriseCertificateInformation) {
        RuoYiConfig.serviceIdByEnterpriseCertificateInformation = serviceIdByEnterpriseCertificateInformation;
    }


    public static String getSeverWebUrl() {
        return severWebUrl;
    }

    public void setSeverWebUrl(String severWebUrl) {
        RuoYiConfig.severWebUrl = severWebUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled() {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled) {
        this.demoEnabled = demoEnabled;
    }

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        RuoYiConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        RuoYiConfig.addressEnabled = addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        RuoYiConfig.captchaType = captchaType;
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath() {
        return getProfile() + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getProfile() + "/upload";
    }
}
