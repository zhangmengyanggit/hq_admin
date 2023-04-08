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

}
