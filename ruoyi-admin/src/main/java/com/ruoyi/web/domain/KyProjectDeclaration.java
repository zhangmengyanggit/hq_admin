package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目申报对象 ky_project_declaration
 * 
 * @author ruoyi
 * @date 2023-03-15
 */
public class KyProjectDeclaration extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 政策原文id */
    @Excel(name = "政策原文id")
    private Long originalPolicyId;

    /** 状态：0进行中，1已结束 */
    @Excel(name = "状态：0进行中，1已结束")
    private Long status;

    /** 申报类别 */
    @Excel(name = "申报类别")
    private String declarationCategory;

    /** 扶持行业 */
    @Excel(name = "扶持行业")
    private String supportingIndustries;

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closingDate;

    /** 申报企业数 */
    @Excel(name = "申报企业数")
    private Long declaredAmount;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOriginalPolicyId(Long originalPolicyId) 
    {
        this.originalPolicyId = originalPolicyId;
    }

    public Long getOriginalPolicyId() 
    {
        return originalPolicyId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setDeclarationCategory(String declarationCategory) 
    {
        this.declarationCategory = declarationCategory;
    }

    public String getDeclarationCategory() 
    {
        return declarationCategory;
    }
    public void setSupportingIndustries(String supportingIndustries) 
    {
        this.supportingIndustries = supportingIndustries;
    }

    public String getSupportingIndustries() 
    {
        return supportingIndustries;
    }
    public void setClosingDate(Date closingDate) 
    {
        this.closingDate = closingDate;
    }

    public Date getClosingDate() 
    {
        return closingDate;
    }
    public void setDeclaredAmount(Long declaredAmount) 
    {
        this.declaredAmount = declaredAmount;
    }

    public Long getDeclaredAmount() 
    {
        return declaredAmount;
    }
    public void setContactNumber(String contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() 
    {
        return contactNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("originalPolicyId", getOriginalPolicyId())
            .append("status", getStatus())
            .append("declarationCategory", getDeclarationCategory())
            .append("supportingIndustries", getSupportingIndustries())
            .append("closingDate", getClosingDate())
            .append("declaredAmount", getDeclaredAmount())
            .append("contactNumber", getContactNumber())
            .toString();
    }
}
