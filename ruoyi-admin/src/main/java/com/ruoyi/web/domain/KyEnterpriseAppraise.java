package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企业用户评价对象 ky_enterprise_appraise
 * 
 * @author ruoyi
 * @date 2023-03-29
 */
public class KyEnterpriseAppraise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业id */
    private Long enterpriseId;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String appraiseContent;

    /** 评价时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "评价时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date appraiseTime;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 企业项目申请表id */
    private Long enterpriseProjectDeclarationId;

    /** 评价结果 */
    @Excel(name = "评价结果")
    private Long starRating;

    /** 评价环节 */
    @Excel(name = "评价环节")
    private Long auditStatus;

    /** 回复内容 */
    @Excel(name = "回复内容")
    private String replyContent;

    /** 评价渠道 */
    @Excel(name = "评价渠道")
    private String irrigationDitch;

    /** 回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date replyTime;

    /** 受理部门 */
    @Excel(name = "受理部门")
    private Long replyDepartment;

    /** 评价用户id */
    private Long sysUserEnterpriseId;

    /** 评价企业名称 */
    private String appraiseEnterpriseName;

    /** 回复状态：默认0未回复，1已回复 */
    private Integer replyStatus;


    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }
    public String getAppraiseEnterpriseName() {
        return appraiseEnterpriseName;
    }

    public void setAppraiseEnterpriseName(String appraiseEnterpriseName) {
        this.appraiseEnterpriseName = appraiseEnterpriseName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setAppraiseContent(String appraiseContent) 
    {
        this.appraiseContent = appraiseContent;
    }

    public String getAppraiseContent() 
    {
        return appraiseContent;
    }
    public void setAppraiseTime(Date appraiseTime) 
    {
        this.appraiseTime = appraiseTime;
    }

    public Date getAppraiseTime() 
    {
        return appraiseTime;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setEnterpriseProjectDeclarationId(Long enterpriseProjectDeclarationId) 
    {
        this.enterpriseProjectDeclarationId = enterpriseProjectDeclarationId;
    }

    public Long getEnterpriseProjectDeclarationId() 
    {
        return enterpriseProjectDeclarationId;
    }
    public void setStarRating(Long starRating) 
    {
        this.starRating = starRating;
    }

    public Long getStarRating() 
    {
        return starRating;
    }
    public void setAuditStatus(Long auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() 
    {
        return auditStatus;
    }
    public void setReplyContent(String replyContent) 
    {
        this.replyContent = replyContent;
    }

    public String getReplyContent() 
    {
        return replyContent;
    }
    public void setIrrigationDitch(String irrigationDitch) 
    {
        this.irrigationDitch = irrigationDitch;
    }

    public String getIrrigationDitch() 
    {
        return irrigationDitch;
    }
    public void setReplyTime(Date replyTime) 
    {
        this.replyTime = replyTime;
    }

    public Date getReplyTime() 
    {
        return replyTime;
    }
    public void setReplyDepartment(Long replyDepartment)
    {
        this.replyDepartment = replyDepartment;
    }

    public Long getReplyDepartment()
    {
        return replyDepartment;
    }
    public void setSysUserEnterpriseId(Long sysUserEnterpriseId) 
    {
        this.sysUserEnterpriseId = sysUserEnterpriseId;
    }

    public Long getSysUserEnterpriseId() 
    {
        return sysUserEnterpriseId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("enterpriseId", getEnterpriseId())
            .append("appraiseContent", getAppraiseContent())
            .append("appraiseTime", getAppraiseTime())
            .append("status", getStatus())
            .append("enterpriseProjectDeclarationId", getEnterpriseProjectDeclarationId())
            .append("starRating", getStarRating())
            .append("auditStatus", getAuditStatus())
            .append("replyContent", getReplyContent())
            .append("irrigationDitch", getIrrigationDitch())
            .append("replyTime", getReplyTime())
            .append("replyDepartment", getReplyDepartment())
            .append("sysUserEnterpriseId", getSysUserEnterpriseId())
            .toString();
    }
}
