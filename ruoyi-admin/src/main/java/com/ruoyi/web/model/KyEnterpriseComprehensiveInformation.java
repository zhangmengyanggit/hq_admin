package com.ruoyi.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * 企业综合信息实例
 * 
 * @author ruoyi
 * @date 2023-04-07
 */
public class KyEnterpriseComprehensiveInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**1科技部_高新技术企业备案基础信息,2科技部_高新技术企业证书信息,3科技部_科技型中小企业创新基金项目信息 */
    private Integer type;
    /**科技部_高新技术企业证书信息 */
    //证书编号
    private String certificateNumber;
    //企业名称
    private String enterpriseName;
    //统一社会信用代码
    private String unifyCode;
    //所属产业
    private String industry;
    //所属省份
    private String province;
    //所属地市
    private String city;
    //所属区县
    private String distinct;
    //证书状态
    private String certificateStatus;
    //认定机关
    private String identificationAuthority;
    //认定日期
    private String determinationDate;

    /**科技部_高新技术企业备案基础信息 */
    //单位名称
    private String name;
    //统一社会代码
    private String cor_code;
    //领域
    private String main_domain;
    //证书编号
    private String certificate_num;

    /**科技部_高新技术企业备案基础信息 */
    //项目编号
    private String prj_num;
    //项目名称
    private String prj_name;
    //单位
    private String company_ame;
    //领域
    private String tech_field;


    public String getPrj_num() {
        return prj_num;
    }

    public void setPrj_num(String prj_num) {
        this.prj_num = prj_num;
    }

    public String getPrj_name() {
        return prj_name;
    }

    public void setPrj_name(String prj_name) {
        this.prj_name = prj_name;
    }

    public String getCompany_ame() {
        return company_ame;
    }

    public void setCompany_ame(String company_ame) {
        this.company_ame = company_ame;
    }

    public String getTech_field() {
        return tech_field;
    }

    public void setTech_field(String tech_field) {
        this.tech_field = tech_field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCor_code() {
        return cor_code;
    }

    public void setCor_code(String cor_code) {
        this.cor_code = cor_code;
    }

    public String getMain_domain() {
        return main_domain;
    }

    public void setMain_domain(String main_domain) {
        this.main_domain = main_domain;
    }

    public String getCertificate_num() {
        return certificate_num;
    }

    public void setCertificate_num(String certificate_num) {
        this.certificate_num = certificate_num;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public String getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(String certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public String getIdentificationAuthority() {
        return identificationAuthority;
    }

    public void setIdentificationAuthority(String identificationAuthority) {
        this.identificationAuthority = identificationAuthority;
    }

    public String getDeterminationDate() {
        return determinationDate;
    }

    public void setDeterminationDate(String determinationDate) {
        this.determinationDate = determinationDate;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getUnifyCode() {
        return unifyCode;
    }

    public void setUnifyCode(String unifyCode) {
        this.unifyCode = unifyCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
