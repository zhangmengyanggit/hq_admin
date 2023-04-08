package com.ruoyi.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 企业申请项目对象 ky_enterprise_project_declaration
 * 
 * @author ruoyi
 * @date 2023-03-15
 */
public class KyEnterpriseProjectDeclaration extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 申请项目表id */
    @Excel(name = "申请项目表id")
    private Long projectDeclarationId;

    /** 企业id */
    @Excel(name = "企业id")
    private Long enterpriseId;

    /** 审核状态：1初审，2复审
 */
    @Excel(name = "审核状态：1初审，2复审")
    private Long auditStatus;

    /** 初审结果：0未初审，1已初审 */
    @Excel(name = "初审结果：0未初审，1已初审")
    private Long firstAuditResult;

    /** 复审结果：0未复审，1已复审 */
    @Excel(name = "复审结果：0未复审，1已复审")
    private Long lastAuditResult;

    /** 申报文件地址 */
    @Excel(name = "申报文件地址")
    private String declaredFileUrl;

    /** 申报公司 */
    @Excel(name = "申报公司")
    private String reportingCompany;

    /** 申报联系人 */
    @Excel(name = "申报联系人")
    private String declarationContact;

    /** 申报联系电话 */
    @Excel(name = "申报联系电话")
    private String contactNumber;

    /** 初审审核人 */
    @Excel(name = "初审审核人")
    private String initialReviewer;

    /** 复审审核人 */
    @Excel(name = "复审审核人")
    private String reviewOfficer;

    /** 初审申报备注 */
    @Excel(name = "初审申报备注")
    private String firstAuditReason;

    /** 复审申报备注 */
    @Excel(name = "复审申报备注")
    private String lastAuditReason;
    /** 部门评价回复 */
    @Excel(name = "部门评价回复")
    private String appraiseAnswer;
    /** 审核人电话 */
    private String  reviewerPhone;
    /** 审核人*/
    private String  reviewer;
    /** 评价时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appraiseTime;
    /** 部门回复时间时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appraiseAnswerTime;
    /** 驳回理由*/
    private String failureReason;

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public Date getAppraiseTime() {
        return appraiseTime;
    }

    public void setAppraiseTime(Date appraiseTime) {
        this.appraiseTime = appraiseTime;
    }

    public Date getAppraiseAnswerTime() {
        return appraiseAnswerTime;
    }

    public void setAppraiseAnswerTime(Date appraiseAnswerTime) {
        this.appraiseAnswerTime = appraiseAnswerTime;
    }

    public String getReviewerPhone() {
        return reviewerPhone;
    }

    public void setReviewerPhone(String reviewerPhone) {
        this.reviewerPhone = reviewerPhone;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getAppraiseAnswer() {
        return appraiseAnswer;
    }

    public void setAppraiseAnswer(String appraiseAnswer) {
        this.appraiseAnswer = appraiseAnswer;
    }

    public String getReviewOfficer() {
        return reviewOfficer;
    }

    public void setReviewOfficer(String reviewOfficer) {
        this.reviewOfficer = reviewOfficer;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProjectDeclarationId(Long projectDeclarationId) 
    {
        this.projectDeclarationId = projectDeclarationId;
    }

    public Long getProjectDeclarationId() 
    {
        return projectDeclarationId;
    }
    public void setEnterpriseId(Long enterpriseId) 
    {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() 
    {
        return enterpriseId;
    }
    public void setAuditStatus(Long auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() 
    {
        return auditStatus;
    }
    public void setFirstAuditResult(Long firstAuditResult) 
    {
        this.firstAuditResult = firstAuditResult;
    }

    public Long getFirstAuditResult() 
    {
        return firstAuditResult;
    }
    public void setLastAuditResult(Long lastAuditResult) 
    {
        this.lastAuditResult = lastAuditResult;
    }

    public Long getLastAuditResult() 
    {
        return lastAuditResult;
    }
    public void setDeclaredFileUrl(String declaredFileUrl) 
    {
        this.declaredFileUrl = declaredFileUrl;
    }

    public String getDeclaredFileUrl() 
    {
        return declaredFileUrl;
    }
    public void setReportingCompany(String reportingCompany) 
    {
        this.reportingCompany = reportingCompany;
    }

    public String getReportingCompany() 
    {
        return reportingCompany;
    }
    public void setDeclarationContact(String declarationContact) 
    {
        this.declarationContact = declarationContact;
    }

    public String getDeclarationContact() 
    {
        return declarationContact;
    }
    public void setContactNumber(String contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() 
    {
        return contactNumber;
    }
    public void setInitialReviewer(String initialReviewer) 
    {
        this.initialReviewer = initialReviewer;
    }

    public String getInitialReviewer() 
    {
        return initialReviewer;
    }

    public void setFirstAuditReason(String firstAuditReason) 
    {
        this.firstAuditReason = firstAuditReason;
    }

    public String getFirstAuditReason() 
    {
        return firstAuditReason;
    }
    public void setLastAuditReason(String lastAuditReason) 
    {
        this.lastAuditReason = lastAuditReason;
    }

    public String getLastAuditReason() 
    {
        return lastAuditReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectDeclarationId", getProjectDeclarationId())
            .append("enterpriseId", getEnterpriseId())
            .append("auditStatus", getAuditStatus())
            .append("firstAuditResult", getFirstAuditResult())
            .append("lastAuditResult", getLastAuditResult())
            .append("declaredFileUrl", getDeclaredFileUrl())
            .append("reportingCompany", getReportingCompany())
            .append("declarationContact", getDeclarationContact())
            .append("contactNumber", getContactNumber())
            .append("initialReviewer", getInitialReviewer())
            .append("review officer", getReviewOfficer())
            .append("firstAuditReason", getFirstAuditReason())
            .append("lastAuditReason", getLastAuditReason())
            .toString();
    }
}
