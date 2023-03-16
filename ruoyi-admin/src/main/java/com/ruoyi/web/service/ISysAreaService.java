package com.ruoyi.web.service;


import com.ruoyi.web.domain.SysArea;

import java.util.List;

/**
 * 地区Service接口
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
public interface ISysAreaService
{
    /**
     * 查询地区
     * 
     * @param regionId 地区主键
     * @return 地区
     */
    public SysArea selectSysRegionByRegionId(Integer regionId);

    /**
     * 查询地区列表
     * 
     * @param sysRegion 地区
     * @return 地区集合
     */
    public List<SysArea> selectSysRegionList(SysArea sysRegion);



    SysArea selectBySysArea(SysArea sysAreaParams);
}
