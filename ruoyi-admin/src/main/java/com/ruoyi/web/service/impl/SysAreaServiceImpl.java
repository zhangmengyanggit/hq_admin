package com.ruoyi.web.service.impl;


import com.ruoyi.web.domain.SysArea;
import com.ruoyi.web.mapper.SysAreaMapper;
import com.ruoyi.web.service.ISysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-09
 */
@Service
public class SysAreaServiceImpl implements ISysAreaService
{
    @Autowired
    private SysAreaMapper sysRegionMapper;

    /**
     * 查询地区
     * 
     * @param regionId 地区主键
     * @return 地区
     */
    @Override
    public SysArea selectSysRegionByRegionId(Integer regionId)
    {
        return sysRegionMapper.selectSysRegionByRegionId(regionId);
    }

    /**
     * 查询地区列表
     * 
     * @param sysRegion 地区
     * @return 地区
     */
    @Override
    public List<SysArea> selectSysRegionList(SysArea sysRegion)
    {
        return sysRegionMapper.selectSysRegionList(sysRegion);
    }

    @Override
    public SysArea selectBySysArea(SysArea sysAreaParams) {
        return sysRegionMapper.selectBySysArea(sysAreaParams);
    }

}
