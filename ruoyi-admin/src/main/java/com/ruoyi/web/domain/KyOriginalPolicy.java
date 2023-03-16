package com.ruoyi.web.domain;

import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 政策原文对象 ky_original_policy
 *
 * @author ruoyi
 * @date 2023-03-15
 */
public class KyOriginalPolicy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String tittle;

    /** 政策原文内容 */
    private String content;

    /** 政策级别：1国家，2湖北省，3宜昌市，4县/区 */
    @Excel(name = "政策级别：1国家，2湖北省，3宜昌市，4县/区")
    private String policyLevel;

    /** 发布部门 */
    private String publishingDepartment;

    /** 政策类别 */
    @Excel(name = "政策类别")
    private String policyCategory;

    /** 产业分类 */
    @Excel(name = "产业分类")
    private String applicableIndustries;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 展示状态 */
    @Excel(name = "展示状态")
    private String state;

    /** 附件地址 */
    @Excel(name = "附件地址")
    private String appendixUrl;

    /** 发布单位 */
    @Excel(name = "发布单位")
    private String publishUnit;

    /** 发布人 */
    @Excel(name = "发布人")
    private String publisher;

    /** 资料地址 */
    @Excel(name = "资料地址")
    private String meansUrl;

    /** 项目状态：0默认项目（进行中），1已结束 */
    @Excel(name = "项目状态：0默认项目", readConverterExp = "进=行中")
    private String itemStatus;

    /** 标签id集合（，分隔） */
    @Excel(name = "标签id集合", readConverterExp = "，=分隔")
    private String tagIds;

    /** 发布状态，默认0：0待解读，1已解读，2已发布 */
    @Excel(name = "发布状态，默认0：0待解读，1已解读，2已发布")
    private Long publishStatus;

    /** 是否有效：默认0有效，1无效 */
    @Excel(name = "是否有效：默认0有效，1无效")
    private Long validStatus;

    /** 有效截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate validDate;

    /** 是否已申请兑现：默认0无申请，1有申请 */
    @Excel(name = "是否已申请兑现")
    private Long applyForCash;

    /** 兑现联系人 */
    @Excel(name = "兑现联系人")
    private String redeemContact;

    /** 兑现联系人电话 */
    @Excel(name = "兑现联系人电话")
    private String redeemContactPhone;

    /** 行业 */
    @Excel(name = "行业")
    private String trade;
    /** 省份 */
    private Integer province;

    /** 市 */
    private Integer city;

    /** 区 */
    private Integer region;
    /** 街道 */
    private Integer street;


    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getStreet() {
        return street;
    }

    public void setStreet(Integer street) {
        this.street = street;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTittle(String tittle)
    {
        this.tittle = tittle;
    }

    public String getTittle()
    {
        return tittle;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setPolicyLevel(String policyLevel)
    {
        this.policyLevel = policyLevel;
    }

    public String getPolicyLevel()
    {
        return policyLevel;
    }
    public void setPublishingDepartment(String publishingDepartment)
    {
        this.publishingDepartment = publishingDepartment;
    }

    public String getPublishingDepartment()
    {
        return publishingDepartment;
    }
    public void setPolicyCategory(String policyCategory)
    {
        this.policyCategory = policyCategory;
    }

    public String getPolicyCategory()
    {
        return policyCategory;
    }
    public void setApplicableIndustries(String applicableIndustries)
    {
        this.applicableIndustries = applicableIndustries;
    }

    public String getApplicableIndustries()
    {
        return applicableIndustries;
    }
    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
    public void setAppendixUrl(String appendixUrl)
    {
        this.appendixUrl = appendixUrl;
    }

    public String getAppendixUrl()
    {
        return appendixUrl;
    }
    public void setPublishUnit(String publishUnit)
    {
        this.publishUnit = publishUnit;
    }

    public String getPublishUnit()
    {
        return publishUnit;
    }
    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getPublisher()
    {
        return publisher;
    }
    public void setMeansUrl(String meansUrl)
    {
        this.meansUrl = meansUrl;
    }

    public String getMeansUrl()
    {
        return meansUrl;
    }
    public void setItemStatus(String itemStatus)
    {
        this.itemStatus = itemStatus;
    }

    public String getItemStatus()
    {
        return itemStatus;
    }
    public void setTagIds(String tagIds)
    {
        this.tagIds = tagIds;
    }

    public String getTagIds()
    {
        return tagIds;
    }
    public void setPublishStatus(Long publishStatus)
    {
        this.publishStatus = publishStatus;
    }

    public Long getPublishStatus()
    {
        return publishStatus;
    }
    public void setValidStatus(Long validStatus)
    {
        this.validStatus = validStatus;
    }

    public Long getValidStatus()
    {
        return validStatus;
    }
    public void setValidDate(LocalDate  validDate)
    {
        this.validDate = validDate;
    }

    public LocalDate  getValidDate()
    {
        return validDate;
    }
    public void setApplyForCash(Long applyForCash)
    {
        this.applyForCash = applyForCash;
    }

    public Long getApplyForCash()
    {
        return applyForCash;
    }
    public void setRedeemContact(String redeemContact)
    {
        this.redeemContact = redeemContact;
    }

    public String getRedeemContact()
    {
        return redeemContact;
    }
    public void setRedeemContactPhone(String redeemContactPhone)
    {
        this.redeemContactPhone = redeemContactPhone;
    }

    public String getRedeemContactPhone()
    {
        return redeemContactPhone;
    }
    public void setTrade(String trade)
    {
        this.trade = trade;
    }

    public String getTrade()
    {
        return trade;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tittle", getTittle())
                .append("content", getContent())
                .append("policyLevel", getPolicyLevel())
                .append("publishingDepartment", getPublishingDepartment())
                .append("policyCategory", getPolicyCategory())
                .append("applicableIndustries", getApplicableIndustries())
                .append("publishTime", getPublishTime())
                .append("state", getState())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("appendixUrl", getAppendixUrl())
                .append("publishUnit", getPublishUnit())
                .append("publisher", getPublisher())
                .append("meansUrl", getMeansUrl())
                .append("itemStatus", getItemStatus())
                .append("tagIds", getTagIds())
                .append("publishStatus", getPublishStatus())
                .append("validStatus", getValidStatus())
                .append("validDate", getValidDate())
                .append("applyForCash", getApplyForCash())
                .append("redeemContact", getRedeemContact())
                .append("redeemContactPhone", getRedeemContactPhone())
                .append("trade", getTrade())
                .toString();
    }
}