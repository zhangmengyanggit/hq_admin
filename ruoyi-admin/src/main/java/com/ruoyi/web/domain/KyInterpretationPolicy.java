package com.ruoyi.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 政策解读对象 ky_interpretation_policy
 * 
 * @author ruoyi
 * @date 2023-03-13
 */
public class KyInterpretationPolicy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 标题 */
    private String tittle;

    /** 政策解读内容 */
    private String content;

    /** 政策原文id */
    private Long originalPolicyId;


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
    public void setOriginalPolicyId(Long originalPolicyId) 
    {
        this.originalPolicyId = originalPolicyId;
    }

    public Long getOriginalPolicyId() 
    {
        return originalPolicyId;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tittle", getTittle())
            .append("content", getContent())
            .append("originalPolicyId", getOriginalPolicyId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
