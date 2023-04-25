package com.ruoyi.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业证书详情对象 ky_enterprise_certificate
 * 
 * @author ruoyi
 * @date 2023-04-21
 */
public class KyEnterpriseCertificate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 企业id */
    @Excel(name = "企业id")
    private Long kyEnterpriseId;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String certificateNumber;

    /** 社会统一信用代码号 */
    @Excel(name = "社会统一信用代码号")
    private String socialUnifiedCreditCodeNumber;

    /** 产业名称 */
    @Excel(name = "产业名称")
    private String industry;

    /** 产业id */
    @Excel(name = "产业id")
    private Long industryId;

    /** 0有效，1无效 */
    @Excel(name = "0有效，1无效")
    private String certificateStatus;

    /** 认定机关 */
    @Excel(name = "认定机关")
    private String identificationAuthority;

    /** 认定日期 */
    @Excel(name = "认定日期")
    private String determinationDate;

    /** 所属省份 */
    @Excel(name = "所属省份")
    private String province;

    /** 所属地市 */
    @Excel(name = "所属地市")
    private String city;

    /** 所属区县 */
    @Excel(name = "所属区县")
    private String distinct;

    /** 证书类型：1高新企业 */
    @Excel(name = "证书类型：1高新企业")
    private Long type;
    /** 企业名称*/
    private String enterpriseName;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setKyEnterpriseId(Long kyEnterpriseId) 
    {
        this.kyEnterpriseId = kyEnterpriseId;
    }

    public Long getKyEnterpriseId() 
    {
        return kyEnterpriseId;
    }
    public void setCertificateNumber(String certificateNumber) 
    {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateNumber() 
    {
        return certificateNumber;
    }
    public void setSocialUnifiedCreditCodeNumber(String socialUnifiedCreditCodeNumber) 
    {
        this.socialUnifiedCreditCodeNumber = socialUnifiedCreditCodeNumber;
    }

    public String getSocialUnifiedCreditCodeNumber() 
    {
        return socialUnifiedCreditCodeNumber;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setIndustryId(Long industryId) 
    {
        this.industryId = industryId;
    }

    public Long getIndustryId() 
    {
        return industryId;
    }
    public void setCertificateStatus(String certificateStatus)
    {
        this.certificateStatus = certificateStatus;
    }

    public String getCertificateStatus()
    {
        return certificateStatus;
    }
    public void setIdentificationAuthority(String identificationAuthority) 
    {
        this.identificationAuthority = identificationAuthority;
    }

    public String getIdentificationAuthority() 
    {
        return identificationAuthority;
    }
    public void setDeterminationDate(String determinationDate) 
    {
        this.determinationDate = determinationDate;
    }

    public String getDeterminationDate() 
    {
        return determinationDate;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistinct(String distinct) 
    {
        this.distinct = distinct;
    }

    public String getDistinct() 
    {
        return distinct;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("kyEnterpriseId", getKyEnterpriseId())
            .append("certificateNumber", getCertificateNumber())
            .append("socialUnifiedCreditCodeNumber", getSocialUnifiedCreditCodeNumber())
            .append("industry", getIndustry())
            .append("industryId", getIndustryId())
            .append("certificateStatus", getCertificateStatus())
            .append("identificationAuthority", getIdentificationAuthority())
            .append("determinationDate", getDeterminationDate())
            .append("province", getProvince())
            .append("city", getCity())
            .append("distinct", getDistinct())
            .append("type", getType())
            .toString();
    }
}
