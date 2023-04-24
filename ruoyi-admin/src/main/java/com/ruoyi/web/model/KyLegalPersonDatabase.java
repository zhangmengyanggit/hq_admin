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
 * 企业F法人库对象
 * 
 * @author ruoyi
 * @date 2023-03-08
 */
public class KyLegalPersonDatabase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String  hy_code ;// 行业代码 -(行业名称)
    private String  shxy_code ;// 社会信用代码 -
    private String  cj_date  ;// 创建时间 -
    private String  jyfw  ;// 经营范围 -
    private String qy_stutas  ;// 企业状态 -
    private String fddbr_name  ;// 法定代表人 -
    private String  zczb  ;// 注册资本 -
    private String  qy_name  ;// 企业名称 -
    private String  qy_type;//企业类型 -（性质）
    private String  djjg  ;// 登记机关

    public String getHy_code() {
        return hy_code;
    }

    public void setHy_code(String hy_code) {
        this.hy_code = hy_code;
    }

    public String getShxy_code() {
        return shxy_code;
    }

    public void setShxy_code(String shxy_code) {
        this.shxy_code = shxy_code;
    }

    public String getCj_date() {
        return cj_date;
    }

    public void setCj_date(String cj_date) {
        this.cj_date = cj_date;
    }

    public String getJyfw() {
        return jyfw;
    }

    public void setJyfw(String jyfw) {
        this.jyfw = jyfw;
    }

    public String getQy_stutas() {
        return qy_stutas;
    }

    public void setQy_stutas(String qy_stutas) {
        this.qy_stutas = qy_stutas;
    }

    public String getFddbr_name() {
        return fddbr_name;
    }

    public void setFddbr_name(String fddbr_name) {
        this.fddbr_name = fddbr_name;
    }

    public String getZczb() {
        return zczb;
    }

    public void setZczb(String zczb) {
        this.zczb = zczb;
    }

    public String getQy_name() {
        return qy_name;
    }

    public void setQy_name(String qy_name) {
        this.qy_name = qy_name;
    }

    public String getQy_type() {
        return qy_type;
    }

    public void setQy_type(String qy_type) {
        this.qy_type = qy_type;
    }

    public String getDjjg() {
        return djjg;
    }

    public void setDjjg(String djjg) {
        this.djjg = djjg;
    }
}
