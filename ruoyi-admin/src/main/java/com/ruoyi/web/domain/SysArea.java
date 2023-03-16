package com.ruoyi.web.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地区对象 sys_region
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public class SysArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 地区主键编号 */
    private Integer id;

    /** 上级ID */
    @Excel(name = "上级ID")
    private Integer pid;

    /** 类型等级  1省份  2地级市 3区县 4 街道 */
    @Excel(name = "类型等级")
    private Integer type;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String name;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 繁体名称 */
    @Excel(name = "繁体名称")
    private String traname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTraname() {
        return traname;
    }

    public void setTraname(String traname) {
        this.traname = traname;
    }

    @Override
    public String toString() {
        return "SysArea{" +
                "id=" + id +
                ", pid=" + pid +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", traname='" + traname + '\'' +
                '}';
    }
}
